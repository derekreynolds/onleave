package com.evolvingreality.onleave.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.HolidayCalendar;
import com.evolvingreality.onleave.model.PublicHoliday;


/**
 * Spring Data JPA repository for the {@link PublicHoliday} entity.
 */
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Long> {
	
	Page<PublicHoliday> findByHolidayCalendarAndHolidayDateBetween(final HolidayCalendar holidayCalendar, 
					final Date startDate, final Date endDate, final Pageable pageable);
	
	Optional<PublicHoliday> findOneByHolidayCalendarAndHolidayDate(final HolidayCalendar holidayCalendar, final Date holidayDate);
}