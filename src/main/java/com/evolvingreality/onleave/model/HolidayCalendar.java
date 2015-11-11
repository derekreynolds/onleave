package com.evolvingreality.onleave.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Derek Reynolds
 *
 */
@Entity
@Table(name = "HOLIDAY_CALENDAR")
public class HolidayCalendar extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @NotNull
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "holidayCalendar")
	private List<PublicHoliday> publicHolidays = new LinkedList<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PublicHoliday> getPublicHolidays() {
		return publicHolidays;
	}

	public void setPublicHolidays(List<PublicHoliday> publicHolidays) {
		this.publicHolidays = publicHolidays;
	}
	
}

