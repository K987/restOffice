/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.entity.employee.Employee;
import hun.restoffice.persistence.entity.employee.EmployeeShift;
import hun.restoffice.persistence.entity.employee.Shift;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 *  Shift persistence interface
 *
 * @author kalmankostenszky
 */
@Local
public interface ShiftServiceLocal {


	/**
	 * reads schedules between two dates
	 * 
	 * @param from
	 * @param to
	 * @return
	 * @throws PersistenceServiceException
	 */
	List<Shift> readCalendarSchedule(Calendar from, Calendar to) throws PersistenceServiceException;

	/**
	 * removes employee from future shifts
	 * 
	 * @param Employee
	 * @return
	 * @throws PersistenceServiceException
	 */
	List<Shift> removeEmployeeFromShift(Employee Employee) throws PersistenceServiceException;


	/**
	 * updates EmployeeShifts batch
	 * 
	 * @param from
	 * @throws PersistenceServiceException
	 */
	void updateShifts(List<EmployeeShift> toClose) throws PersistenceServiceException;

	/**
	 * read service by rowId
	 * 
	 * @param rowId
	 * @return
	 */
	EmployeeShift readByRowId(int rowId) throws PersistenceServiceException;

}
