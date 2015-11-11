package com.evolvingreality.onleave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Derek Reynolds
 *
 */
@Entity
@Table(name = "ENTITLEMENT")
public class Entitlement extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "annual_leave")
	private Double annualLeave;
	
	@Column(name = "additional_annual_leave")
	private Double additionalAnnualLeave;

	@ManyToOne
	private User user;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
		
	public Double getAnnualLeave() {
		return annualLeave;
	}

	public void setAnnualLeave(Double annualLeave) {
		this.annualLeave = annualLeave;
	}

	public Double getAdditionalAnnualLeave() {
		return additionalAnnualLeave;
	}

	public void setAdditionalAnnualLeave(Double additionalAnnualLeave) {
		this.additionalAnnualLeave = additionalAnnualLeave;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
