package com.evolvingreality.onleave.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.HolidayCalendar;


/**
 * Spring Data JPA repository for the {@link HolidayCalendar} entity.
 */
public interface HolidayCalendarRepository extends JpaRepository<HolidayCalendar, Long> {
	
	
}