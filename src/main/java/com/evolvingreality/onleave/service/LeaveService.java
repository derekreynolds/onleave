package com.evolvingreality.onleave.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.User;

/**
 * This services provides operation for the {@link Leave}. Each {@link User} has
 * {@link Leave} for each year. The user can request to take {@link Leave}, their manager must
 * approve the {@link Leave}.
 *  
 * @author Derek Reynolds
 *
 */
public interface LeaveService extends EntityService<Leave> {

	/**
	 * Checks to see if there is already and approved or requested annual leave for the 
	 * time period requested. 
	 * @param user - the {@link User} we are checking under.
	 * @param leave - the {@link Leave} request that we are using to verify.
	 * @return true if there is already an requested or approved annual leave request.
	 */
	Boolean hasAnnualLeaveRequestAlready(final User user, final Leave leave);
	
	/**
	 * Gets all the annual {@link Leave} for the year specified.
	 * @param user - the {@link User} we a looking up for.
	 * @param year - the year we are looking up for.
	 * @param pageable - the {@link Pageable} 
	 * @return a {@link Page} of {@link Leaves}. 
	 */
	Page<Leave> getAnnualLeaveForYear(final User user, final Integer year, final Pageable pageable);
	
	/**
	 * Get all the {@link Leave} for a {@link User}.
	 * @param user - the {@link User} we want the {@link Leave} for.
	 * @param pageable - the {@link Pageable} 
	 * @return a {@link Page} of {@link Leave}
	 */
	Page<Leave> getAnnualLeaveForUser(final User user, final Pageable pageable);
	
}
