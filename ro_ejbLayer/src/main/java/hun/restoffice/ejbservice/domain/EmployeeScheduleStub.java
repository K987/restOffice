/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import hun.restoffice.persistence.entity.employee.EmployeeShift;

/**
 * DTO for employee schedule
 *
 * @author kalmankostenszky
 */
public class EmployeeScheduleStub {

	private final String name;
	private Boolean active;

	private final List<EmployeeWorkDay> workdays;

	public EmployeeScheduleStub(String employeeName, Boolean isActive, Set<EmployeeShift> employeeShifts) {
		this.name = employeeName;
		this.active = isActive;
		this.workdays = new ArrayList<>();
		for (EmployeeShift employeeShift : employeeShifts) {
			if (employeeShift.getActualStart() != null) {
				workdays.add(new EmployeeWorkDay(employeeShift.getShift().getStartDate(), employeeShift.getShift().getStartTime(),
						employeeShift.getShift().getDuration(), employeeShift.getActualStart(), employeeShift.getActualEnd()));
			} else {
				workdays.add(new EmployeeWorkDay(employeeShift.getShift().getStartDate(), employeeShift.getShift().getStartTime(),
						employeeShift.getShift().getDuration()));
			}
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the workdays
	 */
	public List<EmployeeWorkDay> getWorkdays() {
		return workdays;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("EmployeeScheduleStub [name=%s, active=%s, workdays=%s]", name, active, workdays);
	}

	private class EmployeeWorkDay implements Comparable<EmployeeWorkDay> {

		private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		private final Calendar start;
		private final BigDecimal duration;
		private Calendar actualStart = null;
		private Calendar actualEnd = null;

		public EmployeeWorkDay(Date startDate, Date startTime, BigDecimal duration) {
			this.start = Calendar.getInstance();
			this.start.setTime(startDate);
			Calendar time = Calendar.getInstance();
			time.setTime(startTime);
			this.start.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
			this.start.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
			this.duration = duration;

		}

		public EmployeeWorkDay(Date startDate, Date startTime, BigDecimal duration, Date actualStart, Date actualEnd) {
			this(startDate, startTime, duration);

			this.actualStart = (Calendar) this.start.clone();
			this.actualEnd = (Calendar) this.start.clone();

			Calendar tmp = Calendar.getInstance();

			tmp.setTime(actualStart);
			this.actualStart.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
			this.actualStart.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));

			tmp.setTime(actualEnd);
			this.actualEnd.set(Calendar.HOUR_OF_DAY, tmp.get(Calendar.HOUR_OF_DAY));
			this.actualEnd.set(Calendar.MINUTE, tmp.get(Calendar.MINUTE));
		}

		/**
		 * @return the start
		 */
		public Calendar getStart() {
			return this.start;
		}

		/**
		 * @return the duration
		 */
		public BigDecimal getDuration() {
			return duration;
		}

		/**
		 * @return the actualStart
		 */
		public Calendar getActualStart() {
			return this.actualStart;
		}

		/**
		 * @return the actualEnd
		 */
		public Calendar getActualEnd() {
			return this.actualEnd;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.format("EmployeeWorkDay [df=%s, start=%s, duration=%s, actualStart=%s, actualEnd=%s]", df, start, duration, actualStart, actualEnd);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(EmployeeWorkDay o) {
			if (o == null || o.getStart() == null || this.getStart() == null)
				return 0;
			return this.getStart().compareTo(o.getStart());
		}

	}
}
