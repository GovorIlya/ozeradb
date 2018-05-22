package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.OzeradbApp;

import com.mycompany.myapp.domain.UnitDescription;
import com.mycompany.myapp.repository.UnitDescriptionRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UnitDescriptionResource REST controller.
 *
 * @see UnitDescriptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzeradbApp.class)
public class UnitDescriptionResourceIntTest {

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATE_YEAR = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_YEAR = "BBBBBBBBBB";

    private static final String DEFAULT_SQUARE = "AAAAAAAAAA";
    private static final String UPDATED_SQUARE = "BBBBBBBBBB";

    private static final String DEFAULT_COLLESCTORS = "AAAAAAAAAA";
    private static final String UPDATED_COLLESCTORS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRST = 1;
    private static final Integer UPDATED_PRST = 2;

    private static final String DEFAULT_SBROS = "AAAAAAAAAA";
    private static final String UPDATED_SBROS = "BBBBBBBBBB";

    @Autowired
    private UnitDescriptionRepository unitDescriptionRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUnitDescriptionMockMvc;

    private UnitDescription unitDescription;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UnitDescriptionResource unitDescriptionResource = new UnitDescriptionResource(unitDescriptionRepository);
        this.restUnitDescriptionMockMvc = MockMvcBuilders.standaloneSetup(unitDescriptionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitDescription createEntity(EntityManager em) {
        UnitDescription unitDescription = new UnitDescription()
            .city(DEFAULT_CITY)
            .createYear(DEFAULT_CREATE_YEAR)
            .square(DEFAULT_SQUARE)
            .collesctors(DEFAULT_COLLESCTORS)
            .prst(DEFAULT_PRST)
            .sbros(DEFAULT_SBROS);
        return unitDescription;
    }

    @Before
    public void initTest() {
        unitDescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnitDescription() throws Exception {
        int databaseSizeBeforeCreate = unitDescriptionRepository.findAll().size();

        // Create the UnitDescription
        restUnitDescriptionMockMvc.perform(post("/api/unit-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitDescription)))
            .andExpect(status().isCreated());

        // Validate the UnitDescription in the database
        List<UnitDescription> unitDescriptionList = unitDescriptionRepository.findAll();
        assertThat(unitDescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        UnitDescription testUnitDescription = unitDescriptionList.get(unitDescriptionList.size() - 1);
        assertThat(testUnitDescription.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testUnitDescription.getCreateYear()).isEqualTo(DEFAULT_CREATE_YEAR);
        assertThat(testUnitDescription.getSquare()).isEqualTo(DEFAULT_SQUARE);
        assertThat(testUnitDescription.getCollesctors()).isEqualTo(DEFAULT_COLLESCTORS);
        assertThat(testUnitDescription.getPrst()).isEqualTo(DEFAULT_PRST);
        assertThat(testUnitDescription.getSbros()).isEqualTo(DEFAULT_SBROS);
    }

    @Test
    @Transactional
    public void createUnitDescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitDescriptionRepository.findAll().size();

        // Create the UnitDescription with an existing ID
        unitDescription.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitDescriptionMockMvc.perform(post("/api/unit-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitDescription)))
            .andExpect(status().isBadRequest());

        // Validate the UnitDescription in the database
        List<UnitDescription> unitDescriptionList = unitDescriptionRepository.findAll();
        assertThat(unitDescriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUnitDescriptions() throws Exception {
        // Initialize the database
        unitDescriptionRepository.saveAndFlush(unitDescription);

        // Get all the unitDescriptionList
        restUnitDescriptionMockMvc.perform(get("/api/unit-descriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitDescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].createYear").value(hasItem(DEFAULT_CREATE_YEAR.toString())))
            .andExpect(jsonPath("$.[*].square").value(hasItem(DEFAULT_SQUARE.toString())))
            .andExpect(jsonPath("$.[*].collesctors").value(hasItem(DEFAULT_COLLESCTORS.toString())))
            .andExpect(jsonPath("$.[*].prst").value(hasItem(DEFAULT_PRST)))
            .andExpect(jsonPath("$.[*].sbros").value(hasItem(DEFAULT_SBROS.toString())));
    }

    @Test
    @Transactional
    public void getUnitDescription() throws Exception {
        // Initialize the database
        unitDescriptionRepository.saveAndFlush(unitDescription);

        // Get the unitDescription
        restUnitDescriptionMockMvc.perform(get("/api/unit-descriptions/{id}", unitDescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(unitDescription.getId().intValue()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.createYear").value(DEFAULT_CREATE_YEAR.toString()))
            .andExpect(jsonPath("$.square").value(DEFAULT_SQUARE.toString()))
            .andExpect(jsonPath("$.collesctors").value(DEFAULT_COLLESCTORS.toString()))
            .andExpect(jsonPath("$.prst").value(DEFAULT_PRST))
            .andExpect(jsonPath("$.sbros").value(DEFAULT_SBROS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUnitDescription() throws Exception {
        // Get the unitDescription
        restUnitDescriptionMockMvc.perform(get("/api/unit-descriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnitDescription() throws Exception {
        // Initialize the database
        unitDescriptionRepository.saveAndFlush(unitDescription);
        int databaseSizeBeforeUpdate = unitDescriptionRepository.findAll().size();

        // Update the unitDescription
        UnitDescription updatedUnitDescription = unitDescriptionRepository.findOne(unitDescription.getId());
        // Disconnect from session so that the updates on updatedUnitDescription are not directly saved in db
        em.detach(updatedUnitDescription);
        updatedUnitDescription
            .city(UPDATED_CITY)
            .createYear(UPDATED_CREATE_YEAR)
            .square(UPDATED_SQUARE)
            .collesctors(UPDATED_COLLESCTORS)
            .prst(UPDATED_PRST)
            .sbros(UPDATED_SBROS);

        restUnitDescriptionMockMvc.perform(put("/api/unit-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUnitDescription)))
            .andExpect(status().isOk());

        // Validate the UnitDescription in the database
        List<UnitDescription> unitDescriptionList = unitDescriptionRepository.findAll();
        assertThat(unitDescriptionList).hasSize(databaseSizeBeforeUpdate);
        UnitDescription testUnitDescription = unitDescriptionList.get(unitDescriptionList.size() - 1);
        assertThat(testUnitDescription.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testUnitDescription.getCreateYear()).isEqualTo(UPDATED_CREATE_YEAR);
        assertThat(testUnitDescription.getSquare()).isEqualTo(UPDATED_SQUARE);
        assertThat(testUnitDescription.getCollesctors()).isEqualTo(UPDATED_COLLESCTORS);
        assertThat(testUnitDescription.getPrst()).isEqualTo(UPDATED_PRST);
        assertThat(testUnitDescription.getSbros()).isEqualTo(UPDATED_SBROS);
    }

    @Test
    @Transactional
    public void updateNonExistingUnitDescription() throws Exception {
        int databaseSizeBeforeUpdate = unitDescriptionRepository.findAll().size();

        // Create the UnitDescription

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUnitDescriptionMockMvc.perform(put("/api/unit-descriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(unitDescription)))
            .andExpect(status().isCreated());

        // Validate the UnitDescription in the database
        List<UnitDescription> unitDescriptionList = unitDescriptionRepository.findAll();
        assertThat(unitDescriptionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUnitDescription() throws Exception {
        // Initialize the database
        unitDescriptionRepository.saveAndFlush(unitDescription);
        int databaseSizeBeforeDelete = unitDescriptionRepository.findAll().size();

        // Get the unitDescription
        restUnitDescriptionMockMvc.perform(delete("/api/unit-descriptions/{id}", unitDescription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UnitDescription> unitDescriptionList = unitDescriptionRepository.findAll();
        assertThat(unitDescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnitDescription.class);
        UnitDescription unitDescription1 = new UnitDescription();
        unitDescription1.setId(1L);
        UnitDescription unitDescription2 = new UnitDescription();
        unitDescription2.setId(unitDescription1.getId());
        assertThat(unitDescription1).isEqualTo(unitDescription2);
        unitDescription2.setId(2L);
        assertThat(unitDescription1).isNotEqualTo(unitDescription2);
        unitDescription1.setId(null);
        assertThat(unitDescription1).isNotEqualTo(unitDescription2);
    }
}
