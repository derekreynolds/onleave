package com.evolvingreality.onleave.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.evolvingreality.onleave.domain.Day;
import com.evolvingreality.onleave.domain.Month;
import com.evolvingreality.onleave.domain.Year;


/**
 * @author Derek Reynolds
 *
 */
@Service
public class CalendarServiceImpl implements CalendarService {

	@Override
	public Year get(Integer yearInt) {		
		
		LocalDate date = LocalDate.of(yearInt, java.time.Month.JANUARY, 1);

		Year year = new Year();
		
		year.setYear(yearInt);
		
		for(int i = 0; i < 12; i++)
			year.getMonths().add(create(date, date.plusMonths(i)));				
				
		return year;
	}
	
	
	private Month create(final LocalDate year, final LocalDate localMonth) {
		
		Month month = new Month();		
				
		month.setMonth(localMonth.getMonth());
		
		for(int i = 0; i < localMonth.getMonth().length(year.isLeapYear()); i++)
			month.getDays().add(create(localMonth.plusDays(i)));
			
		createWeeks(month);
		
		return month;
	}
	
	private Day create(final LocalDate localDay) {
		
		return new Day(localDay.getDayOfWeek(), localDay.getDayOfMonth(), localDay.getDayOfYear());
		
	}
	
	private void createWeeks(final Month month) {
				
		month.setWeeks(createWeeks(padMonthEnd(padMonthStart(month.getDays()))));
				
	}
	
	private List<Day> padMonthStart(final List<Day> days) {
		
		List<Day> paddedDays = new ArrayList<>(days);
				
		for(int i = 0; i < 6; i++) {
			Day day = paddedDays.get(0);
			if(day.getDayOfWeek() != DayOfWeek.SUNDAY)
				paddedDays.add(0, new Day(day.getDayOfWeek().minus(1), 0, 0));
			else
				break;
		}
		
		return paddedDays;
	}
	
	private List<Day> padMonthEnd(final List<Day> days) {
		
		List<Day> paddedDays = new ArrayList<>(days);
		
		for(int i = days.size(); i < 42; i++) {
			Day day = paddedDays.get(i - 1);
			paddedDays.add(new Day(day.getDayOfWeek().plus(1), 0, 0));
		}
		
		return paddedDays;
	}
	
	private Map<Integer, List<Day>> createWeeks(List<Day> days) {
		
		Map<Integer, List<Day>> weeks = new HashMap<>();
		
		int count = 1;
		
		List<Day> week = new ArrayList<>();
		
		for(int i = 0; i < 42; i++) {			
			
			week.add(days.get(i));
			
			if((i + 1) % 7 == 0) {
				weeks.put(count++, new ArrayList<>(week));
				week.clear();				
			}			
		}
		
		return weeks;
			
	}
}