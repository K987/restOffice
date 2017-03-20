/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.EmployeeScheduleStub;
import hun.restoffice.ejbservice.domain.EmployeeStub;
import hun.restoffice.ejbservice.domain.ShiftStub;
import hun.restoffice.ejbservice.exception.AdaptorException;

/**
 * Employee business facade
 *
 * @author kalmankostenszky
 */
@Local
public interface EmployeeFacadeLocal {

	/**
	 * Retrieve all employees
	 * 
	 * @return
	 * @throws AdaptorException
	 */
	List<EmployeeStub> getAllEmployees() throws AdaptorException;

	/**
	 * add new employee
	 * 
	 * @param employee
	 * @return
	 * @throws AdaptorException
	 */
	EmployeeStub addEmployee(EmployeeStub employee) throws AdaptorException;

	/**
	 * remove employee if employee has shifts before today active will be set to false
	 * 
	 * @param employeeName
	 * @return
	 * @throws AdaptorException
	 */
	List<ShiftStub> removeEmployee(String employeeName) throws AdaptorException;

	/**
	 * Get schedule of employee between two dates
	 * 
	 * @param name
	 * @param f
	 * @param t
	 * @return
	 * @throws AdaptorException
	 */
	EmployeeScheduleStub getEmployeeSchedule(String name, Calendar from, Calendar to) throws AdaptorException;

	/**
	 * Update employee data
	 * 
	 * @param employee
	 * @return
	 * @throws AdaptorException
	 */
	EmployeeStub updateEmployee(EmployeeStub employee) throws AdaptorException;

}
