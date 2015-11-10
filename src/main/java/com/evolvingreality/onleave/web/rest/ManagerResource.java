package com.evolvingreality.onleave.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.evolvingreality.onleave.domain.SelectOption;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.repository.UserRepository;
import com.evolvingreality.onleave.service.ManagerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/v1.0")
public class ManagerResource {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    private ManagerService managerService;

    @Autowired
    public ManagerResource(final ManagerService managerService){
    	this.managerService = managerService;
    }
    
    /**
     * GET  /manager/selectoption -> get all manager select options.
     */
    @RequestMapping(value = "/manager/selectoption",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<SelectOption> getAll() {
        log.debug("REST request to get all Users");
        return managerService.getManagerSelectOptions();
    }


}
