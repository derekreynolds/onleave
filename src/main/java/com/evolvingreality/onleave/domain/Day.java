package com.evolvingreality.onleave.domain;

import java.time.DayOfWeek;

/**
 * @author Derek Reynolds
 *
 */
public class Day {

	private DayOfWeek dayOfWeek;
	
	private Integer dayOfMonth;

	private Integer dayOfYear;
	
	public Day(DayOfWeek dayOfWeek, Integer dayOfMonth, Integer dayOfYear) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public Integer getDayOfYear() {
		return dayOfYear;
	}

	
}