/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.math.BigDecimal;

import hun.restoffice.persistence.entity.employee.Employee;
import hun.restoffice.persistence.entity.employee.JobPosition;

/**
 * DTO for employee  
 *
 * @author kalmankostenszky
 */
public class EmployeeStub {

	private String name;
	private JobPosition position;
	private BigDecimal wage;
	private Boolean active;

	public EmployeeStub(){
		
	}
	
	public EmployeeStub(Employee emp) {
		this.name = emp.getEmployeeName();
		this.position = emp.getDefaultPosition();
		this.wage = emp.getDefaultHourlyWage();
		this.active = emp.IsActive();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the position
	 */
	public JobPosition getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(JobPosition position) {
		this.position = position;
	}

	/**
	 * @return the wage
	 */
	public BigDecimal getWage() {
		return wage;
	}

	/**
	 * @param wage the wage to set
	 */
	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

	/**
	 * @return the active
	 */
	public Boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("EmployeeStub [name=%s, position=%s, wage=%s, active=%s]", name, position, wage, active);
	}
	
	

}
