package com.evolvingreality.onleave.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import com.evolvingreality.onleave.service.util.DateTimeUtil;

public class DateTimeUtilTest {

	private Integer year = new Integer(2015);
	
	@Test
	public void testGetStartOfYear() {
				
		DateTime startOfYear = DateTimeUtil.getStartOfYear(year);
		
		assertThat(startOfYear.getYear()).isEqualTo(year);
		assertThat(startOfYear.getMonthOfYear()).isEqualTo(1);
		assertThat(startOfYear.getDayOfMonth()).isEqualTo(1);
		assertThat(startOfYear.getHourOfDay()).isEqualTo(0);
		assertThat(startOfYear.getMinuteOfHour()).isEqualTo(0);
		assertThat(startOfYear.getSecondOfMinute()).isEqualTo(0);
	}

	@Test
	public void testGetEndOfYear() {
				
		DateTime endOfYear = DateTimeUtil.getEndOfYear(year);
		
		assertThat(endOfYear.getYear()).isEqualTo(year);
		assertThat(endOfYear.getMonthOfYear()).isEqualTo(12);
		assertThat(endOfYear.getDayOfMonth()).isEqualTo(31);
		assertThat(endOfYear.getHourOfDay()).isEqualTo(23);
		assertThat(endOfYear.getMinuteOfHour()).isEqualTo(59);
		assertThat(endOfYear.getSecondOfMinute()).isEqualTo(59);
	}

}
