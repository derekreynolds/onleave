package com.evolvingreality.onleave.service;

import com.evolvingreality.onleave.model.Entitlement;
import com.evolvingreality.onleave.model.User;

/**
 * This services provides operation for the {@link Entitlement}. Each {@link User} has
 * an {@link Entitlement} for each year. It holds the number of days off they are entitled to.  
 *  
 * @author Derek Reynolds
 *
 */
public interface EntitlementServiceImpl extends EntityService<Entitlement> {

	/**
	 * Checks to see if an {@link Entitlement} exists for the {@link User} for a 
	 * particular year.
	 * @param user - the {@link User} that 
	 * @param year - the year we are checking to see if an {@link Entitlement} exists for.
	 * @return true if the {@link Entitlement} exists.
	 */
	Boolean hasUserEntitlementForYear(final User user, final Integer year);

}