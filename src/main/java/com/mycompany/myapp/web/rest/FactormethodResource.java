package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.web.rest.vm.FactormethodSaveVM;
import com.mycompany.myapp.web.rest.vm.FactormethodLoadVM;
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
 * REST controller for managing Factormethod.
 */
@RestController
@RequestMapping("/api/factormethod")
public class FactormethodResource {

    private final Logger log = LoggerFactory.getLogger(FactormethodResource.class);

    /**
     * POST  /factormethod : Save factormethod.
     *
     * @param factormethodSaveVM the factormethod to save
     * @return the ResponseEntity with status 201 (Created) and with body the new FactormethodSaveVM, or with status 400 (Bad Request) if the factormethod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/factormethod")
    @Timed
    public ResponseEntity postFactormethod(@RequestBody FactormethodSaveVM factormethodSaveVM) throws URISyntaxException {
        log.debug("REST request to save FactormethodSaveVM : {}", factormethodSaveVM);
        //TODO please code the save of page data.
        return ResponseEntity.ok().build();
    }
    /**
     * GET  /factormethod : get factormethod.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the factormethodLoadVM, or with status 404 (Not Found)
     */
    @GetMapping("/factormethod")
    @Timed
    public ResponseEntity<FactormethodLoadVM> getFactormethod() {
        log.debug("REST request to get FactormethodLoadVM");
        FactormethodLoadVM factormethodLoadVM = null;
        //TODO please code the load referential data or any utils data to load the page.
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(factormethodLoadVM));
    }


}
