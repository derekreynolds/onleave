package com.evolvingreality.onleave.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.LeaveStatus;
import com.evolvingreality.onleave.model.LeaveType;
import com.evolvingreality.onleave.model.User;


/**
 * Spring Data JPA repository for the {@link Leave} entity.
 */
public interface LeaveRepository extends JpaRepository<Leave, Long> {
	
	Page<Leave> findByUserAndStartDateTimeBetweenAndLeaveTypeInAndLeaveStatusInOrderByStartDateTimeDesc(final User user, 
			final Date startDate, final Date endDate, final Collection<LeaveType> leaveTypes, 
			final Collection<LeaveStatus> leaveStatuses, final Pageable pageable);
	
	List<Leave> findByUserAndStartDateTimeBetweenAndLeaveTypeInAndLeaveStatusInOrderByStartDateTimeDesc(final User user, 
			final Date startDate, final Date endDate, final Collection<LeaveType> leaveTypes, 
			final Collection<LeaveStatus> leaveStatuses);
	
	Page<Leave> findByUserAndLeaveTypeInOrderByStartDateTimeDesc(final User user, final Collection<LeaveType> leaveTypes, final Pageable pageable);
	
}