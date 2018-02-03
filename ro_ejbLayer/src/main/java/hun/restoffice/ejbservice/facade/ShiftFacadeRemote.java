package hun.restoffice.ejbservice.facade;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import hun.restoffice.ejbservice.domain.CalendarScheduleStub;
import hun.restoffice.ejbservice.domain.EmployeeShiftStub;
import hun.restoffice.ejbservice.exception.FacadeException;

/**
 * 
 * Remote interface for ShiftFacade EJB 
 *
 * @author kalmankostenszky
 */
@Remote
public interface ShiftFacadeRemote{

	/**
	 * Retruns the work schedule of a given day
	 * @param day
	 * @return
	 * @throws FacadeException
	 */
	List<CalendarScheduleStub> getCalendarschedule(Calendar day) throws FacadeException;

	/**
	 * Closes existing shifts
	 * @param models
	 * @throws FacadeException
	 */
	void batchShiftClose(List<EmployeeShiftStub> models) throws FacadeException;

}
