package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.EntitlementServiceImpl;
import com.evolvingreality.onleave.service.UserService;
import com.evolvingreality.onleave.web.propertyeditors.LocaleDateTimeEditor;
import com.evolvingreality.onleave.web.rest.util.UriUtil;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;


/**
 * REST controller for getting the {@link Entitlement}.
 */
@RestController
@RequestMapping("/api/v1.0")
public class EntitlementResource {

    private EntitlementServiceImpl entitlementService;
    
    private UserService userService;
    
    @Autowired
    public EntitlementResource(final EntitlementServiceImpl entitlementService, final UserService userService) {
    	this.entitlementService = entitlementService;
    	this.userService = userService;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }

    /**
     * Returns a {@link Page} of {@link Entitlement}.
     * @param pageable - the {@link Pageable} contains the offset in to list we want.
     * @return {@link Page} of {@link Entitlement}
     */
    @RequestMapping(value = "/entitlement",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Page<Entitlement> getAll(final Pageable pageable) {
        return entitlementService.getPage(pageable);
    }

    /**
     * Returns a {@link Entitlement} based on the id sent in.
     * @param id - the id of the {@link Entitlement} we want.
     * @return a {@link Entitlement}
     */
    @RequestMapping(value = "/entitlement/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Entitlement> get(@PathVariable("id") final Long id) {
    	
    	Optional<Entitlement> entitlement = entitlementService.get(id);
    	
    	if(!entitlement.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);    		
    		
        return new ResponseEntity<Entitlement>(entitlement.get(), HttpStatus.OK);
    }

    /**
     * Creates a new {@link Entitlement}.
     * @param userId - the id of the {@link User} that the entitlement belongs to.
     * @param entitlement - the  {@link Entitlement} to create.
     * @return confirmation that the {@link Entitlement} was created or conflict if it already exists.
     */
    @RequestMapping(value = "/user/{userId}/entitlement",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> post(@PathVariable("userId") Long userId, @RequestBody final Entitlement entitlement) {
    	    	
    	Optional<User> user = userService.get(userId);
    	
    	if(entitlementService.hasUserEntitlementForYear(user.get(), entitlement.getYear()))
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();    		
    		
    	entitlementService.save(entitlement);

        return ResponseEntity.created(UriUtil.buildGetUri(this, entitlement.getId())).build();
    }
    
    /**
     * Updates the {@link Entitlement}.
     * @param entitlement - the {@link Entitlement} to update.
     * @return confirmation of the update and the updated {@link Entitlement}.
     */
    @RequestMapping(value = "/entitlement",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Entitlement> update(@RequestBody final Entitlement entitlement) {
    	
    	entitlementService.save(entitlement);   	
    	
        return ResponseEntity.ok().body(entitlement);
    }
 
    /**
     * Deletes a {@link Entitlement}.
     * @param id - the id of the {@link Entitlement} to delete.
     * @return confirmation that the {@link Entitlement} was deleted.
     */
    @RequestMapping(value = "/entitlement/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    	
    	entitlementService.delete(id);    	
   	
        return ResponseEntity.noContent().build();
    }
    
}

