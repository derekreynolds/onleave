package com.evolvingreality.onleave.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.evolvingreality.onleave.domain.Month;
import com.evolvingreality.onleave.domain.Year;

/**
 * @author dr78758
 *
 */
public class CalendarServiceImplTest {

	private CalendarService calendarService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {		
		calendarService = new CalendarServiceImpl();
	}
	
	/**
	 * Test method for {@link com.evolvingreality.onleave.service.CalendarServiceImpl#get(java.lang.Integer)}.
	 */
	@Test
	public void testGet() {
		
		Integer yearNumber = 2015;
		
		Year year = calendarService.get(yearNumber);
		
		assertThat(year).isNotNull();
		assertThat(year.getYear()).isEqualTo(yearNumber);
		assertThat(year.getMonths()).hasSize(12);
		
		Month jan = year.getMonths().get(0);
		
		assertThat(jan.getMonth()).isEqualByComparingTo(java.time.Month.JANUARY);
		assertThat(jan.getDays()).hasSize(31);
		
		Month dec = year.getMonths().get(11);
		
		assertThat(dec.getMonth()).isEqualByComparingTo(java.time.Month.DECEMBER);
		assertThat(dec.getDays()).hasSize(31);
		
	}
	
	@Test
	public void testGetLeapYear() {
		
		Integer yearNumber = 2016;
		
		Year year = calendarService.get(yearNumber);
		
		assertThat(year).isNotNull();
		assertThat(year.getYear()).isEqualTo(yearNumber);
		assertThat(year.getMonths()).hasSize(12);
		
		Month feb = year.getMonths().get(1);
		
		assertThat(feb.getMonth()).isEqualByComparingTo(java.time.Month.FEBRUARY);
		assertThat(feb.getDays()).hasSize(29);
		
	}

}

