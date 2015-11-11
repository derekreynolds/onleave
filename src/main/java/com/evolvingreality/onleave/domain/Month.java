package com.evolvingreality.onleave.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Month {

	private java.time.Month month;
	
	private Map<Integer, List<Day>> weeks = new HashMap<>();
	
	private List<Day> days = new ArrayList<>();

	
	public java.time.Month getMonth() {
		return month;
	}

	public void setMonth(java.time.Month month) {
		this.month = month;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	public  Map<Integer, List<Day>> getWeeks() {
		return weeks;
	}

	public void setWeeks(Map<Integer, List<Day>> weeks) {
		this.weeks = weeks;
	}	
	
}