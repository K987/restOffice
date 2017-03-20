/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.ShiftStub;
import hun.restoffice.persistence.entity.employee.EmployeeShift;
import hun.restoffice.persistence.entity.employee.Shift;
import hun.restoffice.remoteClient.domain.CalendarScheduleStub;
import hun.restoffice.remoteClient.domain.EmployeeShiftStub;

/**
 * Convert shift entity and stub back and forth
 *
 * @author kalmankostenszky
 */
@Stateless
public class ShiftConverter implements ShiftConverterLocal {

	/**
	 * @param shift
	 * @return
	 */
	private CalendarScheduleStub to(Shift shift) {
		List<EmployeeShiftStub> empShfits = new ArrayList<>();
		for (EmployeeShift item : shift.getEmployeeShifts()) {
			empShfits.add(new EmployeeShiftStub(item.getActualStart(), item.getRowId(), item.getActualEnd(),
					(item.getActualPosition() == null ? -1 : item.getActualPosition().ordinal()), item.getEmployee().getEmployeeName(),
					item.getEmployee().getDefaultPosition().ordinal()));
		}

		return new CalendarScheduleStub(shift.getStartDate(), shift.getStartTime(), shift.getId(), empShfits);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.ShiftConverterLocal#to(java.util.List)
	 */
	@Override
	public List<CalendarScheduleStub> toSchedule(List<Shift> shifts) {
		List<CalendarScheduleStub> rtrn = new ArrayList<>();
		for (Shift s : shifts) {
			rtrn.add(to(s));
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.ShiftConverterLocal#to(java.util.List)
	 */
	@Override
	public List<ShiftStub> to(List<Shift> shifts) {
		List<ShiftStub> rtrn = new ArrayList<>();
		for (Shift s : shifts) {
			rtrn.add(new ShiftStub(s));
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.ShiftConverterLocal#from(java.util.List)
	 */
	@Override
	public List<EmployeeShift> from(List<EmployeeShiftStub> models) {
		List<EmployeeShift> rtrn = new ArrayList<>();
		for (EmployeeShiftStub stub : models) {
			rtrn.add(from(stub));
		}
		return rtrn;
	}

	private EmployeeShift from(EmployeeShiftStub stub) {
		return new EmployeeShift(stub.getName(), stub.getShiftid(), stub.getActualStart().getTime(), stub.getActualEnd().getTime(), stub.getActualPosition());
	}

}
