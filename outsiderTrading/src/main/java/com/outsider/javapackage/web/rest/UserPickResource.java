package com.outsider.javapackage.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.outsider.javapackage.domain.UserPick;
import com.outsider.javapackage.service.UserPickService;
import com.outsider.javapackage.web.rest.util.HeaderUtil;
import com.outsider.javapackage.service.dto.UserPickCriteria;
import com.outsider.javapackage.service.UserPickQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserPick.
 */
@RestController
@RequestMapping("/api")
public class UserPickResource {

    private final Logger log = LoggerFactory.getLogger(UserPickResource.class);

    private static final String ENTITY_NAME = "userPick";

    private final UserPickService userPickService;

    private final UserPickQueryService userPickQueryService;

    public UserPickResource(UserPickService userPickService, UserPickQueryService userPickQueryService) {
        this.userPickService = userPickService;
        this.userPickQueryService = userPickQueryService;
    }

    /**
     * POST  /user-picks : Create a new userPick.
     *
     * @param userPick the userPick to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userPick, or with status 400 (Bad Request) if the userPick has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-picks")
    @Timed
    public ResponseEntity<UserPick> createUserPick(@Valid @RequestBody UserPick userPick) throws URISyntaxException {
        log.debug("REST request to save UserPick : {}", userPick);
        if (userPick.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new userPick cannot already have an ID")).body(null);
        }
        UserPick result = userPickService.save(userPick);
        return ResponseEntity.created(new URI("/api/user-picks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-picks : Updates an existing userPick.
     *
     * @param userPick the userPick to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userPick,
     * or with status 400 (Bad Request) if the userPick is not valid,
     * or with status 500 (Internal Server Error) if the userPick couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-picks")
    @Timed
    public ResponseEntity<UserPick> updateUserPick(@Valid @RequestBody UserPick userPick) throws URISyntaxException {
        log.debug("REST request to update UserPick : {}", userPick);
        if (userPick.getId() == null) {
            return createUserPick(userPick);
        }
        UserPick result = userPickService.save(userPick);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userPick.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-picks : get all the userPicks.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of userPicks in body
     */
    @GetMapping("/user-picks")
    @Timed
    public ResponseEntity<List<UserPick>> getAllUserPicks(UserPickCriteria criteria) {
        log.debug("REST request to get UserPicks by criteria: {}", criteria);
        List<UserPick> entityList = userPickQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /user-picks/:id : get the "id" userPick.
     *
     * @param id the id of the userPick to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userPick, or with status 404 (Not Found)
     */
    @GetMapping("/user-picks/{id}")
    @Timed
    public ResponseEntity<UserPick> getUserPick(@PathVariable Long id) {
        log.debug("REST request to get UserPick : {}", id);
        UserPick userPick = userPickService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userPick));
    }

    /**
     * DELETE  /user-picks/:id : delete the "id" userPick.
     *
     * @param id the id of the userPick to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-picks/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserPick(@PathVariable Long id) {
        log.debug("REST request to delete UserPick : {}", id);
        userPickService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
