package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.OzeradbApp;

import com.mycompany.myapp.OzeradbApp;
import com.mycompany.myapp.domain.ResearchMethod;
import com.mycompany.myapp.repository.ResearchMethodRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ResearchMethodResource REST controller.
 *
 * @see ResearchMethodResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzeradbApp.class)
public class ResearchMethodResourceIntTest {

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    @Autowired
    private ResearchMethodRepository researchMethodRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResearchMethodMockMvc;

    private ResearchMethod researchMethod;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResearchMethodResource researchMethodResource = new ResearchMethodResource(researchMethodRepository);
        this.restResearchMethodMockMvc = MockMvcBuilders.standaloneSetup(researchMethodResource)
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
    public static ResearchMethod createEntity(EntityManager em) {
        ResearchMethod researchMethod = new ResearchMethod()
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE);
        return researchMethod;
    }

    @Before
    public void initTest() {
        researchMethod = createEntity(em);
    }

    @Test
    @Transactional
    public void createResearchMethod() throws Exception {
        int databaseSizeBeforeCreate = researchMethodRepository.findAll().size();

        // Create the ResearchMethod
        restResearchMethodMockMvc.perform(post("/api/research-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(researchMethod)))
            .andExpect(status().isCreated());

        // Validate the ResearchMethod in the database
        List<ResearchMethod> researchMethodList = researchMethodRepository.findAll();
        assertThat(researchMethodList).hasSize(databaseSizeBeforeCreate + 1);
        ResearchMethod testResearchMethod = researchMethodList.get(researchMethodList.size() - 1);
        assertThat(testResearchMethod.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testResearchMethod.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createResearchMethodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = researchMethodRepository.findAll().size();

        // Create the ResearchMethod with an existing ID
        researchMethod.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResearchMethodMockMvc.perform(post("/api/research-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(researchMethod)))
            .andExpect(status().isBadRequest());

        // Validate the ResearchMethod in the database
        List<ResearchMethod> researchMethodList = researchMethodRepository.findAll();
        assertThat(researchMethodList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResearchMethods() throws Exception {
        // Initialize the database
        researchMethodRepository.saveAndFlush(researchMethod);

        // Get all the researchMethodList
        restResearchMethodMockMvc.perform(get("/api/research-methods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(researchMethod.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))));
    }

    @Test
    @Transactional
    public void getResearchMethod() throws Exception {
        // Initialize the database
        researchMethodRepository.saveAndFlush(researchMethod);

        // Get the researchMethod
        restResearchMethodMockMvc.perform(get("/api/research-methods/{id}", researchMethod.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(researchMethod.getId().intValue()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)));
    }

    @Test
    @Transactional
    public void getNonExistingResearchMethod() throws Exception {
        // Get the researchMethod
        restResearchMethodMockMvc.perform(get("/api/research-methods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResearchMethod() throws Exception {
        // Initialize the database
        researchMethodRepository.saveAndFlush(researchMethod);
        int databaseSizeBeforeUpdate = researchMethodRepository.findAll().size();

        // Update the researchMethod
        ResearchMethod updatedResearchMethod = researchMethodRepository.findOne(researchMethod.getId());
        // Disconnect from session so that the updates on updatedResearchMethod are not directly saved in db
        em.detach(updatedResearchMethod);
        updatedResearchMethod
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);

        restResearchMethodMockMvc.perform(put("/api/research-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedResearchMethod)))
            .andExpect(status().isOk());

        // Validate the ResearchMethod in the database
        List<ResearchMethod> researchMethodList = researchMethodRepository.findAll();
        assertThat(researchMethodList).hasSize(databaseSizeBeforeUpdate);
        ResearchMethod testResearchMethod = researchMethodList.get(researchMethodList.size() - 1);
        assertThat(testResearchMethod.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testResearchMethod.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingResearchMethod() throws Exception {
        int databaseSizeBeforeUpdate = researchMethodRepository.findAll().size();

        // Create the ResearchMethod

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResearchMethodMockMvc.perform(put("/api/research-methods")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(researchMethod)))
            .andExpect(status().isCreated());

        // Validate the ResearchMethod in the database
        List<ResearchMethod> researchMethodList = researchMethodRepository.findAll();
        assertThat(researchMethodList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResearchMethod() throws Exception {
        // Initialize the database
        researchMethodRepository.saveAndFlush(researchMethod);
        int databaseSizeBeforeDelete = researchMethodRepository.findAll().size();

        // Get the researchMethod
        restResearchMethodMockMvc.perform(delete("/api/research-methods/{id}", researchMethod.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ResearchMethod> researchMethodList = researchMethodRepository.findAll();
        assertThat(researchMethodList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResearchMethod.class);
        ResearchMethod researchMethod1 = new ResearchMethod();
        researchMethod1.setId(1L);
        ResearchMethod researchMethod2 = new ResearchMethod();
        researchMethod2.setId(researchMethod1.getId());
        assertThat(researchMethod1).isEqualTo(researchMethod2);
        researchMethod2.setId(2L);
        assertThat(researchMethod1).isNotEqualTo(researchMethod2);
        researchMethod1.setId(null);
        assertThat(researchMethod1).isNotEqualTo(researchMethod2);
    }
}
