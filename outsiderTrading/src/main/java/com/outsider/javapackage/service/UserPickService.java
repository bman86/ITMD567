package com.outsider.javapackage.service;

import com.outsider.javapackage.domain.UserPick;
import com.outsider.javapackage.repository.UserPickRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing UserPick.
 */
@Service
@Transactional
public class UserPickService {

    private final Logger log = LoggerFactory.getLogger(UserPickService.class);

    private final UserPickRepository userPickRepository;

    public UserPickService(UserPickRepository userPickRepository) {
        this.userPickRepository = userPickRepository;
    }

    /**
     * Save a userPick.
     *
     * @param userPick the entity to save
     * @return the persisted entity
     */
    public UserPick save(UserPick userPick) {
        log.debug("Request to save UserPick : {}", userPick);
        return userPickRepository.save(userPick);
    }

    /**
     *  Get all the userPicks.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserPick> findAll() {
        log.debug("Request to get all UserPicks");
        return userPickRepository.findAll();
    }

    /**
     *  Get one userPick by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public UserPick findOne(Long id) {
        log.debug("Request to get UserPick : {}", id);
        return userPickRepository.findOne(id);
    }

    /**
     *  Delete the  userPick by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserPick : {}", id);
        userPickRepository.delete(id);
    }
}
