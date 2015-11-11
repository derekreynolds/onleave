package com.evolvingreality.onleave.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.repository.HolidayCalendarRepository;

/**
 * @author Derek Reynolds
 *
 */
public class HolidayCalendarServiceImplTest {


	@Mock
	private HolidayCalendarRepository holidayCalendarRepository;
	
	private HolidayCalendarService holidayCalendarService;
	
	public HolidayCalendarServiceImplTest() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		holidayCalendarService = new HolidayCalendarServiceImpl(holidayCalendarRepository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.evolvingreality.onleave.service.HolidayCalendarServiceImpl#save(com.evolvingreality.onleave.model.HolidayCalendar)}.
	 */
	@Test
	public void testSave() {
		
		HolidayCalendar holidayCalendar = new HolidayCalendar();
		
		holidayCalendar.setTitle("Test Calendar");
		holidayCalendar.setDescription("Test Description");
		
		holidayCalendarService.save(holidayCalendar);
		
		verify(holidayCalendarRepository, times(1)).save(holidayCalendar);
		
	}

}
