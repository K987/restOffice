/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import hun.restoffice.persistence.entity.employee.Shift;

/**
 *  
 *
 * @author kalmankostenszky
 */
public class ShiftStub {

	
	private final Calendar start;
	private final BigDecimal duration;

	/**
	 * @param s
	 */
	public ShiftStub(Shift s) {
		this.start = Calendar.getInstance();
		start.setTime(s.getStartDate());
		start.set(Calendar.HOUR_OF_DAY, s.getStartTime().getHours());
		start.set(Calendar.MINUTE, s.getStartTime().getMinutes());
		this.duration = s.getDuration();
	}

	/**
	 * @return the start
	 */
	public Calendar getStart() {
		return start;
	}

	/**
	 * @return the duration
	 */
	public BigDecimal getDuration() {
		return duration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ShiftStub [start=%s, duration=%s]", start, duration);
	}

	
}
