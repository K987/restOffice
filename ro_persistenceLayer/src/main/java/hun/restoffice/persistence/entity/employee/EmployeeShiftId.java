package hun.restoffice.persistence.entity.employee;

import java.io.Serializable;

/**
 * The primary key class for the employee_shift database table.
 * 
 * @author kalmankostenszky
 */
public class EmployeeShiftId implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer employee;
	private Integer shift;

	/**
	 * @return the personId
	 */
	public Integer getPerson() {
		return employee;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPerson(Integer employee) {
		this.employee = employee;
	}

	/**
	 * @return the shiftId
	 */
	public Integer getShift() {
		return shift;
	}

	/**
	 * @param shiftId
	 *            the shiftId to set
	 */
	public void setShift(Integer shiftId) {
		this.shift = shiftId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmployeeShiftId)) {
			return false;
		}
		EmployeeShiftId castOther = (EmployeeShiftId) other;
		return (this.shift == castOther.getShift() && this.employee == castOther.getPerson());
	}

	public int hashCode() {

		return (int) ((this.employee + this.shift) * (this.employee + this.shift + 1) / 2.0f + this.employee);
	}
}