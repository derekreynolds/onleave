package com.evolvingreality.onleave.service;

import com.evolvingreality.onleave.model.HolidayCalendar;

public class HolidayCalendarTestUtil {

	/**
	 * Creates a default {@link HolidayCalendar}
	 * @return
	 */
	public static HolidayCalendar create() {
		
		HolidayCalendar holidayCalendar = new HolidayCalendar();
		
		holidayCalendar.setTitle("Test Holiday Calendar");
		holidayCalendar.setDescription("Test Description");

		return holidayCalendar;
	}
	
}