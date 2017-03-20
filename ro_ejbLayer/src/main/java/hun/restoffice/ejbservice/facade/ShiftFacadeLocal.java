/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.exception.AdaptorException;
import hun.restoffice.remoteClient.domain.CalendarScheduleStub;

/**
 *  Shift business facade
 *
 * @author kalmankostenszky
 */
@Local
public interface ShiftFacadeLocal {

	/**
	 * Get schedule of workdays between to dates
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	List<CalendarScheduleStub> getCalendarSchedule(Calendar from, Calendar to) throws AdaptorException;

	
}
