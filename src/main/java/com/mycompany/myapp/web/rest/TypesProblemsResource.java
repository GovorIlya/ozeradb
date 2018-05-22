package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.TypesProblems;

import com.mycompany.myapp.repository.TypesProblemsRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TypesProblems.
 */
@RestController
@RequestMapping("/api")
public class TypesProblemsResource {

    private final Logger log = LoggerFactory.getLogger(TypesProblemsResource.class);

    private static final String ENTITY_NAME = "typesProblems";

    private final TypesProblemsRepository typesProblemsRepository;

    public TypesProblemsResource(TypesProblemsRepository typesProblemsRepository) {
        this.typesProblemsRepository = typesProblemsRepository;
    }

    /**
     * POST  /types-problems : Create a new typesProblems.
     *
     * @param typesProblems the typesProblems to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typesProblems, or with status 400 (Bad Request) if the typesProblems has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/types-problems")
    @Timed
    public ResponseEntity<TypesProblems> createTypesProblems(@RequestBody TypesProblems typesProblems) throws URISyntaxException {
        log.debug("REST request to save TypesProblems : {}", typesProblems);
        if (typesProblems.getId() != null) {
            throw new BadRequestAlertException("A new typesProblems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypesProblems result = typesProblemsRepository.save(typesProblems);
        return ResponseEntity.created(new URI("/api/types-problems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /types-problems : Updates an existing typesProblems.
     *
     * @param typesProblems the typesProblems to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typesProblems,
     * or with status 400 (Bad Request) if the typesProblems is not valid,
     * or with status 500 (Internal Server Error) if the typesProblems couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/types-problems")
    @Timed
    public ResponseEntity<TypesProblems> updateTypesProblems(@RequestBody TypesProblems typesProblems) throws URISyntaxException {
        log.debug("REST request to update TypesProblems : {}", typesProblems);
        if (typesProblems.getId() == null) {
            return createTypesProblems(typesProblems);
        }
        TypesProblems result = typesProblemsRepository.save(typesProblems);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typesProblems.getId().toString()))
            .body(result);
    }

    /**
     * GET  /types-problems : get all the typesProblems.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typesProblems in body
     */
    @GetMapping("/types-problems")
    @Timed
    public List<TypesProblems> getAllTypesProblems() {
        log.debug("REST request to get all TypesProblems");
        return typesProblemsRepository.findAll();
        }

    /**
     * GET  /types-problems/:id : get the "id" typesProblems.
     *
     * @param id the id of the typesProblems to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typesProblems, or with status 404 (Not Found)
     */
    @GetMapping("/types-problems/{id}")
    @Timed
    public ResponseEntity<TypesProblems> getTypesProblems(@PathVariable Long id) {
        log.debug("REST request to get TypesProblems : {}", id);
        TypesProblems typesProblems = typesProblemsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(typesProblems));
    }

    /**
     * DELETE  /types-problems/:id : delete the "id" typesProblems.
     *
     * @param id the id of the typesProblems to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/types-problems/{id}")
    @Timed
    public ResponseEntity<Void> deleteTypesProblems(@PathVariable Long id) {
        log.debug("REST request to delete TypesProblems : {}", id);
        typesProblemsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
