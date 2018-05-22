package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.web.rest.vm.ResearchmethodSaveVM;
import com.mycompany.myapp.web.rest.vm.ResearchmethodLoadVM;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Researchmethod.
 */
@RestController
@RequestMapping("/api/researchmethod")
class ResearchmethodResource {

    private final Logger log = LoggerFactory.getLogger(ResearchmethodResource.class);

    /**
     * POST  /researchmethod : Save researchmethod.
     *
     * @param researchmethodSaveVM the researchmethod to save
     * @return the ResponseEntity with status 201 (Created) and with body the new ResearchmethodSaveVM, or with status 400 (Bad Request) if the researchmethod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/researchmethod")
    @Timed
    public ResponseEntity postResearchmethod(@RequestBody ResearchmethodSaveVM researchmethodSaveVM) throws URISyntaxException {
        log.debug("REST request to save ResearchmethodSaveVM : {}", researchmethodSaveVM);
        //TODO please code the save of page data.
        return ResponseEntity.ok().build();
    }
    /**
     * GET  /researchmethod : get researchmethod.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the researchmethodLoadVM, or with status 404 (Not Found)
     */
    @GetMapping("/researchmethod")
    @Timed
    public ResponseEntity<ResearchmethodLoadVM> getResearchmethod() {
        log.debug("REST request to get ResearchmethodLoadVM");
        ResearchmethodLoadVM researchmethodLoadVM = null;
        //TODO please code the load referential data or any utils data to load the page.
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(researchmethodLoadVM));
    }


}
