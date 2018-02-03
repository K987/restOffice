/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.CalendarScheduleStub;
import hun.restoffice.ejbservice.domain.EmployeeShiftStub;
import hun.restoffice.ejbservice.domain.ShiftStub;
import hun.restoffice.persistence.entity.employee.EmployeeShift;
import hun.restoffice.persistence.entity.employee.Shift;

/**
 * Convert shift entity and stub back and forth
 *
 * @author kalmankostenszky
 */
@Local
public interface ShiftConverterLocal {

	/**
	 * list of shfts to list of schedule stubs
	 * 
	 * @param readCalendarSchedule
	 * @return
	 */
	List<CalendarScheduleStub> toSchedule(List<Shift> readCalendarSchedule);
	
	/**
	 * list of shifts to list of schedule stubs
	 * 
	 * @param shifts
	 * @return
	 */
	List<ShiftStub> to(List<Shift> shifts);

	/**
	 * @param models
	 * @return
	 */
	List<EmployeeShift> from(List<EmployeeShiftStub> models);


}
