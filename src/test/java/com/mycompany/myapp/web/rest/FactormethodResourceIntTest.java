package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.OzeradbApp;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FactormethodResource REST controller.
 *
 * @see FactormethodResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzeradbApp.class)
public class FactormethodResourceIntTest {

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restFactormethodMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FactormethodResource factormethodResource = new FactormethodResource();
        this.restFactormethodMockMvc = MockMvcBuilders.standaloneSetup(factormethodResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }




    @Test
    public void postFactormethod()throws Exception{
    //TODO
    }


    @Test
    public void getFactormethod()throws Exception{
    //TODO
    }
}
