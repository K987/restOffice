/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.EmployeeScheduleStub;
import hun.restoffice.ejbservice.domain.EmployeeStub;
import hun.restoffice.persistence.entity.employee.Employee;

/**
 *  Convert employee entity and stub back and forth
 *
 * @author kalmankostenszky
 */

@Stateless
public class EmployeeConverter implements EmployeeConverterLocal {


	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.EmployeeConverterLocal#to(java.util.List)
	 */
	@Override
	public List<EmployeeStub> to(List<Employee> readAll) {
		List<EmployeeStub> rtrn = new ArrayList<>();
		for (Employee emp : readAll) {
			rtrn.add(new EmployeeStub(emp));
		}
		return rtrn;
	}


	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.EmployeeConverterLocal#to(hun.restoffice.persistence.entity.employee.Employee)
	 */
	@Override
	public EmployeeStub to(Employee create) {
		return new EmployeeStub(create);
	}


	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.EmployeeConverterLocal#from(hun.restoffice.ejbservice.domain.EmployeeStub)
	 */
	@Override
	public Employee from(EmployeeStub employee) {
		Employee rtrn = new Employee();
		rtrn.setEmployeeName(employee.getName());
		rtrn.setDefaultPosition(employee.getPosition());
		rtrn.setActive(employee.isActive());
		rtrn.setDefaultHourlyWage(employee.getWage());
		return rtrn;
	}


	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.EmployeeConverterLocal#toSchedule(hun.restoffice.persistence.entity.employee.Employee)
	 */
	@Override
	public EmployeeScheduleStub toSchedule(Employee queryEmpSchedule) {
		return new EmployeeScheduleStub(queryEmpSchedule.getEmployeeName(), queryEmpSchedule.IsActive(), queryEmpSchedule.getEmployeeShifts());
	}
	
	
}
