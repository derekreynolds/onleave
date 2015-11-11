package com.evolvingreality.onleave.service;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.User;

/**
 * This services provides operation for the {@link HolidayCalendar}. Each {@link User} has
 * a {@link HolidayCalendar}. It contains the {@link PublicHoliday} that the user is entitled to.
 * It is used to calculate how many days a user is taking off when they request leave.
 *  
 * @author Derek Reynolds
 *
 */
public interface HolidayCalendarService extends EntityService<HolidayCalendar> {
	
}