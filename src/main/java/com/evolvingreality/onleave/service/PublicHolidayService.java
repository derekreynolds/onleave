package com.evolvingreality.onleave.service;

import org.joda.time.DateTime;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;

/**
 * This services provides operation for the {@link PublicHoliday}. Each {@link User} has
 * a {@link HolidayCalendar}. It contains the {@link PublicHoliday} that the user is entitled to.
 * It is used to calculate how many days a user is taking off when they request leave.
 *  
 * @author Derek Reynolds
 *
 */
public interface PublicHolidayService extends EntityService<PublicHoliday> {

	Boolean hasHolidayCalendarPublicHolidayForDate(final HolidayCalendar holidayCalendar, final DateTime date);
	
}