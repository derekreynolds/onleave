package com.evolvingreality.onleave.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Derek Reynolds
 *
 */
public class Year {
	
	private Integer year;
	
	private List<Month> months = new ArrayList<>();	

	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Month> getMonths() {
		return months;
	}

	public void setMonths(List<Month> months) {
		this.months = months;
	}	

}