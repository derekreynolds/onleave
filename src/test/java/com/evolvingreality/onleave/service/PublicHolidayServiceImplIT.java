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
import com.evolvingreality.onleave.model.PublicHoliday;

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
public class PublicHolidayServiceImplIT {

	@Autowired
	private PublicHolidayService publicHolidayService;

	@Autowired
	private HolidayCalendarService holidayCalendarService;
	
	
	/**
	 * Test method for {@link com.evolvingreality.onleave.service.PublicHolidayServiceImpl#save(com.evolvingreality.onleave.model.PublicHoliday)}.
	 */
	@Test
	public void testSaveSuccess() {
						
		PublicHoliday publicHoliday = PublicHolidayTestUtil.create();
		
		HolidayCalendar holidayCalendar = HolidayCalendarTestUtil.create();
		
		holidayCalendarService.save(holidayCalendar);
		
		publicHoliday.setHolidayCalendar(holidayCalendar);
		
		publicHolidayService.save(publicHoliday);
		
		assertThat(publicHoliday.getId()).isNotNull();
		assertThat(publicHoliday.getCreatedDate()).isNotNull();
		assertThat(publicHoliday.getCreatedBy()).isEqualTo("system");
		assertThat(publicHoliday.getLastModifiedDate()).isNotNull();
		assertThat(publicHoliday.getLastModifiedBy()).isEqualTo("system");
	}

}
