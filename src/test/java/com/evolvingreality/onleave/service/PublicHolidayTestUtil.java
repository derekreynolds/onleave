package com.evolvingreality.onleave.service;

import org.joda.time.DateTime;

import com.evolvingreality.onleave.model.PublicHoliday;

public class PublicHolidayTestUtil {

	/**
	 * Creates a default {@link PublicHoliday}
	 * @return
	 */
	public static PublicHoliday create() {
		
		PublicHoliday publicHoliday = new PublicHoliday();
		
		publicHoliday.setTitle("Test Public Holiday");
		publicHoliday.setDescription("Test Description");
		publicHoliday.setHolidayDate(DateTime.now());

		return publicHoliday;
	}
	
}

