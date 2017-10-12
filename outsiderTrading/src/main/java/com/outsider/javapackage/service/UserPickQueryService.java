package com.outsider.javapackage.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.outsider.javapackage.domain.UserPick;
import com.outsider.javapackage.domain.*; // for static metamodels
import com.outsider.javapackage.repository.UserPickRepository;
import com.outsider.javapackage.service.dto.UserPickCriteria;


/**
 * Service for executing complex queries for UserPick entities in the database.
 * The main input is a {@link UserPickCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {%link UserPick} or a {@link Page} of {%link UserPick} which fulfills the criterias
 */
@Service
@Transactional(readOnly = true)
public class UserPickQueryService extends QueryService<UserPick> {

    private final Logger log = LoggerFactory.getLogger(UserPickQueryService.class);


    private final UserPickRepository userPickRepository;

    public UserPickQueryService(UserPickRepository userPickRepository) {
        this.userPickRepository = userPickRepository;
    }

    /**
     * Return a {@link List} of {%link UserPick} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UserPick> findByCriteria(UserPickCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<UserPick> specification = createSpecification(criteria);
        return userPickRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {%link UserPick} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UserPick> findByCriteria(UserPickCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<UserPick> specification = createSpecification(criteria);
        return userPickRepository.findAll(specification, page);
    }

    /**
     * Function to convert UserPickCriteria to a {@link Specifications}
     */
    private Specifications<UserPick> createSpecification(UserPickCriteria criteria) {
        Specifications<UserPick> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UserPick_.id));
            }
            if (criteria.getSymbl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSymbl(), UserPick_.symbl));
            }
            if (criteria.getOwn() != null) {
                specification = specification.and(buildSpecification(criteria.getOwn(), UserPick_.own));
            }
            if (criteria.getEntryPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEntryPrice(), UserPick_.entryPrice));
            }
            if (criteria.getUp() != null) {
                specification = specification.and(buildSpecification(criteria.getUp(), UserPick_.up));
            }
            if (criteria.getTarget() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTarget(), UserPick_.target));
            }
            if (criteria.getTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTime(), UserPick_.time));
            }
            if (criteria.getUserId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getUserId(), UserPick_.user, User_.id));
            }
        }
        return specification;
    }

}
