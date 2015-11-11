package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.HolidayCalendarService;
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
 * REST controller for getting the {@link HolidayCalendar}.
 */
@RestController
@RequestMapping("/api/v1.0")
public class HolidayCalendarResource {

    private HolidayCalendarService holidayCalendarService;

    @Autowired
    public HolidayCalendarResource(final HolidayCalendarService holidayCalendarService) {
    	this.holidayCalendarService = holidayCalendarService;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }

    /**
     * Returns a {@link Page} of {@link HolidayCalendar}.
     * @param pageable - the {@link Pageable} contains the offset in to list we want.
     * @return {@link Page} of {@link HolidayCalendar}
     */
    @RequestMapping(value = "/holidaycalendar",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Page<HolidayCalendar> getAll(final Pageable pageable) {
        return holidayCalendarService.getPage(pageable);
    }

    /**
     * Returns a {@link HolidayCalendar} based on the id sent in.
     * @param id - the id of the {@link HolidayCalendar} we want.
     * @return a {@link HolidayCalendar}
     */
    @RequestMapping(value = "/holidaycalendar/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<HolidayCalendar> get(@PathVariable("id") final Long id) {
    	
    	Optional<HolidayCalendar> holidayCalendar = holidayCalendarService.get(id);
    	
    	if(!holidayCalendar.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);    		
    		
        return new ResponseEntity<HolidayCalendar>(holidayCalendar.get(), HttpStatus.OK);
    }

    /**
     * Creates a new {@link HolidayCalendar}.
     * @param holidayCalendar - the {@link HolidayCalendar} to create.
     * @return confirmation that the {@link HolidayCalendar} was created.
     */
    @RequestMapping(value = "/holidaycalendar",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> post(final HolidayCalendar holidayCalendar) {
    	
    	holidayCalendarService.save(holidayCalendar);
    	
        return ResponseEntity.created(UriUtil.buildGetUri(this, holidayCalendar.getId())).build();
    }
    
    /**
     * Updates the {@link HolidayCalendar}.
     * @param holidayCalendar - the {@link HolidayCalendar} to update.
     * @return confirmation of the update and the updated {@link HolidayCalendar}.
     */
    @RequestMapping(value = "/holidaycalendar",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<HolidayCalendar> put(final HolidayCalendar holidayCalendar) {
    	
    	holidayCalendarService.save(holidayCalendar);   	
    	
        return ResponseEntity.ok().body(holidayCalendar);
    }
 
    /**
     * Deletes a {@link HolidayCalendar}.
     * @param id - the id of the {@link HolidayCalendar} to delete.
     * @return confirmation that the {@link HolidayCalendar} was deleted.
     */
    @RequestMapping(value = "/holidaycalendar/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> delete(@PathVariable final Long id) {
    	
    	holidayCalendarService.delete(id);    	
   	
        return ResponseEntity.noContent().build();
    }
    
}