/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.EmployeeScheduleStub;
import hun.restoffice.ejbservice.domain.EmployeeStub;
import hun.restoffice.persistence.entity.employee.Employee;

/**
 * Convert employee entity and stub back and forth
 *
 * @author kalmankostenszky
 */
@Local
public interface EmployeeConverterLocal {

	/**
	 * list of employees to list of employee stubs
	 * 
	 * @param readAll
	 * @return
	 */
	public List<EmployeeStub> to(List<Employee> readAll);

	/**
	 * Employee to stub
	 * 
	 * @param create
	 * @return
	 */
	public EmployeeStub to(Employee create);

	/**
	 * stub to employee
	 * 
	 * @param employee
	 * @return
	 */
	public Employee from(EmployeeStub employee);

	/**
	 * employee to schedule
	 * 
	 * @param queryEmpSchedule
	 * @return
	 */
	public EmployeeScheduleStub toSchedule(Employee queryEmpSchedule);
}
