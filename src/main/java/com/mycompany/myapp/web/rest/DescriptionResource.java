package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.web.rest.vm.DescriptionSaveVM;
import com.mycompany.myapp.web.rest.vm.DescriptionLoadVM;
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
 * REST controller for managing Description.
 */
@RestController
@RequestMapping("/api/description")
public class DescriptionResource {

    private final Logger log = LoggerFactory.getLogger(DescriptionResource.class);

    /**
     * POST  /description : Save description.
     *
     * @param descriptionSaveVM the description to save
     * @return the ResponseEntity with status 201 (Created) and with body the new DescriptionSaveVM, or with status 400 (Bad Request) if the description has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/description")
    @Timed
    public ResponseEntity postDescription(@RequestBody DescriptionSaveVM descriptionSaveVM) throws URISyntaxException {
        log.debug("REST request to save DescriptionSaveVM : {}", descriptionSaveVM);
        //TODO please code the save of page data.
        return ResponseEntity.ok().build();
    }
    /**
     * GET  /description : get description.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the descriptionLoadVM, or with status 404 (Not Found)
     */
    @GetMapping("/description")
    @Timed
    public ResponseEntity<DescriptionLoadVM> getDescription() {
        log.debug("REST request to get DescriptionLoadVM");
        DescriptionLoadVM descriptionLoadVM = null;
        //TODO please code the load referential data or any utils data to load the page.
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(descriptionLoadVM));
    }


}
