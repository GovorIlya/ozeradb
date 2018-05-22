package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.web.rest.vm.TeachstateSaveVM;
import com.mycompany.myapp.web.rest.vm.TeachstateLoadVM;
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
 * REST controller for managing Teachstate.
 */
@RestController
@RequestMapping("/api/teachstate")
public class TeachstateResource {

    private final Logger log = LoggerFactory.getLogger(TeachstateResource.class);

    /**
     * POST  /teachstate : Save teachstate.
     *
     * @param teachstateSaveVM the teachstate to save
     * @return the ResponseEntity with status 201 (Created) and with body the new TeachstateSaveVM, or with status 400 (Bad Request) if the teachstate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teachstate")
    @Timed
    public ResponseEntity postTeachstate(@RequestBody TeachstateSaveVM teachstateSaveVM) throws URISyntaxException {
        log.debug("REST request to save TeachstateSaveVM : {}", teachstateSaveVM);
        //TODO please code the save of page data.
        return ResponseEntity.ok().build();
    }
    /**
     * GET  /teachstate : get teachstate.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the teachstateLoadVM, or with status 404 (Not Found)
     */
    @GetMapping("/teachstate")
    @Timed
    public ResponseEntity<TeachstateLoadVM> getTeachstate() {
        log.debug("REST request to get TeachstateLoadVM");
        TeachstateLoadVM teachstateLoadVM = null;
        //TODO please code the load referential data or any utils data to load the page.
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(teachstateLoadVM));
    }


}
