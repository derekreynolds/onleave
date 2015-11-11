package com.evolvingreality.onleave.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.evolvingreality.onleave.Application;
import com.evolvingreality.onleave.model.HolidayCalendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Derek Reynolds
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class HolidayCalendarServiceImplIT {

	@Autowired
	private HolidayCalendarService holidayCalendarService;

	
	/**
	 * Test method for {@link com.evolvingreality.onleave.serviceHolidayCalendarServiceImpl#save(com.evolvingreality.onleave.model.HolidayCalendar)}.
	 */
	@Test
	public void testSaveSuccess() {
		
		HolidayCalendar holidayCalendar = HolidayCalendarTestUtil.create();
				
		holidayCalendarService.save(holidayCalendar);
		
		assertThat(holidayCalendar.getId()).isNotNull();
		assertThat(holidayCalendar.getCreatedDate()).isNotNull();
		assertThat(holidayCalendar.getCreatedBy()).isEqualTo("system");
		assertThat(holidayCalendar.getLastModifiedDate()).isNotNull();
		assertThat(holidayCalendar.getLastModifiedBy()).isEqualTo("system");
	}

}