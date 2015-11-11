package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.User;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.LeaveService;
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
 * REST controller for getting the {@link Leave}.
 */
@RestController
@RequestMapping("/api/v1.0")
public class LeaveResource {

    private LeaveService leaveService;

    private UserService userService;
    
    @Autowired
    public LeaveResource(final LeaveService leaveService, final UserService userService) {
    	this.leaveService = leaveService;
    	this.userService = userService;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }

    /**
     * Returns a {@link Page} of {@link Leave}.
     * @param pageable - the {@link Pageable} contains the offset in to list we want.
     * @return {@link Page} of {@link Leave}
     */
    @RequestMapping(value = "/user/{userId}/leave",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Page<Leave> getAll(@PathVariable("userId") Long userId, final Pageable pageable) {
    	
        return leaveService.getPage(pageable);
    }

    /**
     * Returns a {@link Leave} based on the id sent in.
     * @param leave - the {@link Leave} we want.
     * @return a {@link Leave}
     */
    @RequestMapping(value = "/leave/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Leave> get(@PathVariable("id") final Long id) {
    	
    	Optional<Leave> leave = leaveService.get(id);
    	
    	if(!leave.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);    		
    		
        return new ResponseEntity<Leave>(leave.get(), HttpStatus.OK);
    }

    /**
     * Creates a new {@link Leave}.
     * @param userId - the id of the {@link User}.
     * @param leave - the {@link Leave} to create for the {@link User}.
     * @return confirmation that the {@link Leave} was created or conflict if it already exists.
     */
    @RequestMapping(value = "/user/{userId}/leave",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> post(@PathVariable Long userId, final Leave leave) {
    	
    	Optional<User> user = userService.get(userId);
    	
    	if(leaveService.hasAnnualLeaveRequestAlready(user.get(), leave))
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();    		
    		
    	leaveService.save(leave);
    	
        return ResponseEntity.created(UriUtil.buildGetUri(this, leave.getId())).build();
    }
    
    /**
     * Updates the {@link Leave}.
     * @param entitlement - the {@link Leave} to update.
     * @return confirmation of the update and the updated {@link Leave}.
     */
    @RequestMapping(value = "/leave",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Leave> update(final Leave leave) {
    	
    	leaveService.save(leave);   	
    	
        return ResponseEntity.ok().body(leave);
    }
 
    /**
     * Deletes a {@link Leave}.
     * @param publicHoliday - the {@link Leave} to delete.
     * @return confirmation that the {@link Leave} was deleted.
     */
    @RequestMapping(value = "/leave/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
    	
    	leaveService.delete(id);    	
   	
        return ResponseEntity.noContent().build();
    }
    
}

