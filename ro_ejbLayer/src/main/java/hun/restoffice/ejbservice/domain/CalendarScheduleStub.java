/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;




/**
 * DTO for calendar schedule
 *
 * @author kalmankostenszky
 */

public class CalendarScheduleStub implements Comparable<CalendarScheduleStub>, Serializable {

	private final Calendar start;
	private final List<EmployeeShiftStub> assignees;
	
	private final int id;

	public CalendarScheduleStub(Date startDate, Date startTime, int id, List<EmployeeShiftStub> employeeShifts) {
		(start = Calendar.getInstance()).setTime(startDate);
		start.set(Calendar.HOUR_OF_DAY, startTime.getHours());
		start.set(Calendar.MINUTE, startTime.getMinutes());
		assignees = employeeShifts;
		this.id = id;
		
	}

	/**
	 * @return the start
	 */
	public Calendar getStart() {
		return start;
	}

	/**
	 * @return the assignees
	 */
	public List<EmployeeShiftStub> getAssignees() {
		return assignees;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CalendarScheduleStub [start=%s, assignees=%s]", start, assignees);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CalendarScheduleStub o) {
		return start.compareTo(o.getStart());
	}


	
	
}


	