package com.evolvingreality.onleave.service;

import org.joda.time.DateTime;

import com.evolvingreality.onleave.model.Leave;
import com.evolvingreality.onleave.model.LeaveStatus;
import com.evolvingreality.onleave.model.LeaveType;


public class LeaveTestUtil {

	/**
	 * Creates a default {@link Leave}
	 * @return
	 */
	public static Leave create() {
		
		DateTime startDateTime = new DateTime(2015, 8, 10, 0, 0, 0);
		DateTime endDateTime = new DateTime(2015, 8, 15, 0, 0, 0);
		
		Leave leave = new Leave();
		
		leave.setLeaveStatus(LeaveStatus.REQUESTED);
		leave.setLeaveType(LeaveType.ANNUAL);
		leave.setStartDateTime(startDateTime);
		leave.setEndDateTime(endDateTime);

		return leave;
	}
	
}