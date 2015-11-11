package com.evolvingreality.onleave.service.util;


import org.joda.time.DateTime;

/**
 * @author Derek Reynolds
 *
 */
public class DateTimeUtil {

	/**
	 * Creates a {@link DateTime} representing the start of the year
	 * @param year the year we want the start date for.
	 * @return a {@link DateTime} representing the start of the year.
	 */
	public static DateTime getStartOfYear(Integer year) {
		return new DateTime(year, 1, 1, 0, 0, 0);
	}
	
	/**
	 * Creates a {@link DateTime} representing the end of the year
	 * @param year the year we want the end date for.
	 * @return a {@link DateTime} representing the end of the year.
	 */
	public static DateTime getEndOfYear(Integer year) {
		return new DateTime(year, 12, 31, 23, 59, 59);	
	}

	
}
