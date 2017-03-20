/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hun.restoffice.persistence.entity.employee.Employee;
import hun.restoffice.persistence.entity.employee.Shift;
import hun.restoffice.persistence.exception.PersistenceExceptionType;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * Employee persistence service
 *
 * @author kalmankostenszky
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EmployeeService implements EmployeeServiceLocal {

	private static final Logger LOG = Logger.getLogger(PartnerService.class);

	@PersistenceContext(unitName = "ro-persistence-unit")
	private EntityManager entityManager;

	@EJB
	private ShiftServiceLocal shiftService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.EmployeeServiceLocal#readAll()
	 */
	@Override
	public List<Employee> readAll() throws PersistenceServiceException {
		List<Employee> rtrn = null;
		try {
			rtrn = this.entityManager.createNamedQuery(Employee.FIND_ALL).getResultList();
			return rtrn;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.EmployeeServiceLocal#create()
	 */
	@Override
	public Employee create(Employee employee) throws PersistenceServiceException {
		if (count(employee.getEmployeeName()) == 0) {
			try {
				Employee rtrn = this.entityManager.merge(employee);
				this.entityManager.flush();
				return rtrn;

			} catch (Exception e) {
				LOG.error(e.getMessage());
				throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
			}
		} else {
			throw new PersistenceServiceException(PersistenceExceptionType.EXISTS_ALREADY, employee.getEmployeeName() + " already exists");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.EmployeeServiceLocal#queryEmpSchedule(java.lang.String,
	 * java.util.Calendar, java.util.Calendar)
	 */
	@Override
	public Employee queryEmpSchedule(String name, Calendar from, Calendar to) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(Employee.GET_EMPLOYEE_SCHEDULE, Employee.class).setParameter(Employee.NAME, name)
					.setParameter(Employee.START_DATE, from.getTime()).setParameter(Employee.END_DATE, to.getTime()).getSingleResult();
		} catch (AmbiguousResolutionException e) {
			LOG.error(e.getMessage());
			throw new PersistenceServiceException(PersistenceExceptionType.AMBIGOUS_RESULT, "multiple matching for name: " + name);
		} catch (NoResultException e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.NOT_EXISTS, "no matching for name: " + name);
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN,
					"unkonow error during querying for [name: " + name + " dates between: " + from + " - " + to);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.EmployeeServiceLocal#removeEmployee(java.lang.String)
	 */
	@Override
	public List<Shift> deleteEmployee(String employeeName) throws PersistenceServiceException {
		List<Shift> rtrn = new ArrayList<>();
		Employee emp = read(employeeName);
		if (!workedBefore(employeeName, Calendar.getInstance())) {
			this.entityManager.remove(emp);
			this.entityManager.flush();
			return rtrn;
		} else {
			LOG.info("else is active: "+ emp != null);
			emp.setActive(false);
			rtrn = this.shiftService.removeEmployeeFromShift(emp);
			this.entityManager.flush();
			return rtrn;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hun.restoffice.persistence.service.EmployeeServiceLocal#updateEmployee(hun.restoffice.persistence.entity.employee
	 * .Employee)
	 */
	@Override
	public Employee updateEmployee(Employee employee) throws PersistenceServiceException {
		Employee emp = read(employee.getEmployeeName());
		emp.setActive(employee.IsActive());
		emp.setDefaultHourlyWage(employee.getDefaultHourlyWage());
		emp.setDefaultPosition(employee.getDefaultPosition());
		this.entityManager.flush();
		return emp;
	}

	/**
	 * Checks if an employee has completed workdays before a date
	 * 
	 * @param employeeName
	 * @param time
	 * @return true: if worked before the given date
	 * @throws PersistenceServiceException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private boolean workedBefore(String employeeName, Calendar time) throws PersistenceServiceException {
		try {
			LOG.info("worked before invoked");
			 return 0 < this.entityManager.createNamedQuery(Employee.COUNT_DAYS_WORKED, Long.class)
					.setParameter(Employee.NAME, employeeName.toLowerCase().trim()).setParameter(Employee.END_DATE, time.getTime()).getSingleResult()
					.intValue();

		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN,
					"unkonow error during querying for [name: " + employeeName + ",[ date: " + time + "]");
		}
	}

	/**
	 * retrieves employee by name
	 * 
	 * @param employeeName
	 * @return
	 * @throws PersistenceServiceException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private Employee read(String employeeName) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(Employee.GET_EMPLOYEE_BY_NAME, Employee.class)
					.setParameter(Employee.NAME, employeeName.toLowerCase().trim()).getSingleResult();
		} catch (AmbiguousResolutionException e) {
			LOG.error(e.getMessage());
			throw new PersistenceServiceException(PersistenceExceptionType.AMBIGOUS_RESULT, "multiple matching for name: " + employeeName);
		} catch (NoResultException e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.NOT_EXISTS, "no matching for name: " + employeeName);
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unkonow error during querying for name: " + employeeName);
		}
	}

	/**
	 * Checks if employee exists by name
	 * 
	 * @param employee
	 * @return
	 * @throws PersistenceServiceException
	 */
	private int count(String employeeName) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(Employee.COUNT, Long.class).setParameter(Employee.NAME, employeeName.toLowerCase().trim())
					.getSingleResult().intValue();
		} catch (Exception e) {
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unkonown excption occured while counting " + employeeName);
		}
	}

}
