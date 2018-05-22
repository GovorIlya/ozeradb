package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.web.rest.vm.ProblemtypesSaveVM;
import com.mycompany.myapp.web.rest.vm.ProblemtypesLoadVM;
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
 * REST controller for managing Problemtypes.
 */
@RestController
@RequestMapping("/api/problemtypes")
public class ProblemtypesResource {

    private final Logger log = LoggerFactory.getLogger(ProblemtypesResource.class);

    /**
     * POST  /problemtypes : Save problemtypes.
     *
     * @param problemtypesSaveVM the problemtypes to save
     * @return the ResponseEntity with status 201 (Created) and with body the new ProblemtypesSaveVM, or with status 400 (Bad Request) if the problemtypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/problemtypes")
    @Timed
    public ResponseEntity postProblemtypes(@RequestBody ProblemtypesSaveVM problemtypesSaveVM) throws URISyntaxException {
        log.debug("REST request to save ProblemtypesSaveVM : {}", problemtypesSaveVM);
        //TODO please code the save of page data.
        return ResponseEntity.ok().build();
    }
    /**
     * GET  /problemtypes : get problemtypes.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the problemtypesLoadVM, or with status 404 (Not Found)
     */
    @GetMapping("/problemtypes")
    @Timed
    public ResponseEntity<ProblemtypesLoadVM> getProblemtypes() {
        log.debug("REST request to get ProblemtypesLoadVM");
        ProblemtypesLoadVM problemtypesLoadVM = null;
        //TODO please code the load referential data or any utils data to load the page.
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(problemtypesLoadVM));
    }


}
