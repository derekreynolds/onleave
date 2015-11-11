
package com.evolvingreality.onleave.web.rest;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;
import com.evolvingreality.onleave.security.AuthoritiesConstants;
import com.evolvingreality.onleave.service.HolidayCalendarService;
import com.evolvingreality.onleave.service.PublicHolidayService;
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
 * REST controller for getting the {@link PublicHoliday}.
 */
@RestController
@RequestMapping("/api/v1.0")
public class PublicHolidayResource {

    private PublicHolidayService publicHolidayService;
    
    private HolidayCalendarService holidayCalendarService;

    @Autowired
    public PublicHolidayResource(final PublicHolidayService publicHolidayService, final HolidayCalendarService holidayCalendarService) {
    	this.publicHolidayService = publicHolidayService;
    	this.holidayCalendarService = holidayCalendarService;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocaleDateTimeEditor("yyyy-MM-dd", false));
    }

    /**
     * Returns a {@link Page} of {@link PublicHoliday}.
     * @param pageable - the {@link Pageable} contains the offset in to list we want.
     * @return {@link Page} of {@link PublicHoliday}
     */
    @RequestMapping(value = "/publicholiday",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Page<PublicHoliday> getAll(final Pageable pageable) {
        return publicHolidayService.getPage(pageable);
    }

    /**
     * Returns a {@link Page} of {@link PublicHoliday} for a particular year.
     * @param year - the year we are looking at.
     * @param pageable - the {@link Pageable} contains the offset in to list we want.
     * @return {@link Page} of {@link PublicHoliday}
     */
    @RequestMapping(value = "/publicholiday/year/{year}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public Page<PublicHoliday> getAllForYear(@PathVariable("year") final Integer year, final Pageable pageable) {
        return publicHolidayService.getPage(pageable);
    }
    
    /**
     * Returns a {@link PublicHoliday} based on the id sent in.
     * @param id - the id of the {@link PublicHoliday} we want.
     * @return a {@link PublicHoliday}
     */
    @RequestMapping(value = "/publicholiday/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<PublicHoliday> get(@PathVariable("id") final Long id) {
    	
    	Optional<PublicHoliday> publicHoliday = publicHolidayService.get(id);
    	
    	if(!publicHoliday.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);    		
    		
        return new ResponseEntity<PublicHoliday>(publicHoliday.get(), HttpStatus.OK);
    }

    /**
     * Creates a new {@link PublicHoliday}.
     * @param holidayCalendarId - the id of the {@link HolidayCalendar}.
     * @param publicHoliday - the {@link PublicHoliday} to create.
     * @return confirmation that the {@link PublicHoliday} was created.
     */
    @RequestMapping(value = "/holidaycalendar/{holidayCalendarId}/publicholiday",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> post(@PathVariable("holidayCalendarId") final Long holidayCalendarId, final PublicHoliday publicHoliday) {
    	
    	Optional<HolidayCalendar> holidayCalendar = holidayCalendarService.get(holidayCalendarId);
    	
    	if(publicHolidayService.hasHolidayCalendarPublicHolidayForDate(holidayCalendar.get(), publicHoliday.getHolidayDate()))
    		return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
    		
    	publicHolidayService.save(publicHoliday);    	

        return ResponseEntity.created(UriUtil.buildGetUri(this, publicHoliday.getId())).build();
    }
    
    /**
     * Updates the {@link PublicHoliday}.
     * @param publicHoliday - the {@link PublicHoliday} to update.
     * @return confirmation of the update and the updated {@link PublicHoliday}.
     */
    @RequestMapping(value = "/publicholiday",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<PublicHoliday> update(final PublicHoliday publicHoliday) {
    	
    	publicHolidayService.save(publicHoliday);   	
    	
        return ResponseEntity.ok().body(publicHoliday);
    }
 
    /**
     * Deletes a {@link PublicHoliday}.
     * @param id - the id of the {@link PublicHoliday} to delete.
     * @return confirmation that the {@link PublicHoliday} was deleted.
     */
    @RequestMapping(value = "/publicholiday/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
    	
    	publicHolidayService.delete(id);    	
   	
        return ResponseEntity.noContent().build();
    }
    
}