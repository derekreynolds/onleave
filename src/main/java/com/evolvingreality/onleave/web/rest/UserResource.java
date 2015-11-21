package com.evolvingreality.onleave.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Inject
    private UserRepository userRepository;

    /**
     * GET  /users -> get all user.
     */
    @RequestMapping(value = "/user",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public Page<User> getAll(Pageable pageable) {
        log.debug("REST request to get all Users");
        return userRepository.findAll(pageable);
    }

    /**
     * GET  /user/:id -> get the user.
     */
    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    ResponseEntity<User> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        return new ResponseEntity<>(userRepository.findOne(id), HttpStatus.OK);

    }
}
