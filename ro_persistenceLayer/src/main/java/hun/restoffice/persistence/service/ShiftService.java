/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import hun.restoffice.persistence.entity.employee.EmployeeShift;
import hun.restoffice.persistence.entity.employee.Shift;
import hun.restoffice.persistence.exception.PersistenceExceptionType;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * Shift persistence service
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName = "ejb/shiftService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ShiftService implements ShiftServiceLocal {

	private static final Logger LOG = Logger.getLogger(ShiftService.class);

	@PersistenceContext(unitName = "ro-persistence-unit")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.ShiftServiceLocal#readCalendarSchedule(java.util.Calendar,
	 * java.util.Calendar)
	 */
	@Override
	public List<Shift> readCalendarSchedule(Calendar from, Calendar to) throws PersistenceServiceException {
		try {
			List<Shift> lst = this.entityManager.createNamedQuery(Shift.GET_SCHEDULE, Shift.class).setParameter(Shift.FROM_DATE, from.getTime())
					.setParameter(Shift.TO_DATE, to.getTime()).getResultList();
			return lst;
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.ShiftServiceLocal#removeEmployeeFromShift(java.lang.String)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Shift> removeEmployeeFromShift(Employee Employee) throws PersistenceServiceException {
		List<Shift> rtrn = new ArrayList<>();
		try {
			List<EmployeeShift> lstEmpShifts = this.entityManager.createNamedQuery(EmployeeShift.GET_ENTITY_AFTER, EmployeeShift.class)
					.setParameter(EmployeeShift.EMPLOYEE, Employee).setParameter(EmployeeShift.FROM_DATE, Calendar.getInstance().getTime()).getResultList();
			for (EmployeeShift es : lstEmpShifts) {
				rtrn.add(es.getShift());
				this.entityManager.remove(es);
			}
			return rtrn;
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private EmployeeShift readByShiftAndName(String name, int shiftId) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(EmployeeShift.FIND_BY_NAME_AND_SHIFT, EmployeeShift.class)
					.setParameter(EmployeeShift.EMPLOYEE_NAME, name.toLowerCase().trim()).setParameter(EmployeeShift.SHIFT_ID, shiftId).getSingleResult();
		} catch (AmbiguousResolutionException e) {
			LOG.error(e.getMessage());
			throw new PersistenceServiceException(PersistenceExceptionType.AMBIGOUS_RESULT, "multiple matching for [" + name + ", " + shiftId + "]");
		} catch (NoResultException e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.NOT_EXISTS, "no matching for [" + name.toLowerCase().trim() + ", " + shiftId + "]");
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.ShiftServiceLocal#updateShifts(java.util.List)
	 */
	@Override
	public void updateShifts(List<EmployeeShift> toClose) throws PersistenceServiceException {
		for (EmployeeShift es : toClose) {
			LOG.info(es.getEmployeeName() +" "+es.getShiftId());
			EmployeeShift employessShift = this.readByShiftAndName(es.getEmployeeName(), es.getShiftId());
			employessShift.setActualStart(es.getActualStart());
			employessShift.setActualEnd(es.getActualEnd());
			employessShift.setActualPosition(es.getActualPosition());
			try {
				LOG.info(employessShift);
				this.entityManager.merge(employessShift);
			} catch (Exception e) {
				LOG.error(e);
				throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN,
						"unknown error happend while merging employeeShift: [" + es.getEmployeeName().toLowerCase().trim() + ", " + es.getShiftId() + "]");
			}
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public EmployeeShift readByRowId(int rowId) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(EmployeeShift.GET_BY_ROWID, EmployeeShift.class).setParameter(EmployeeShift.ROWID, rowId)
					.getSingleResult();
		} catch (AmbiguousResolutionException e) {
			LOG.error(e.getMessage());
			throw new PersistenceServiceException(PersistenceExceptionType.AMBIGOUS_RESULT, "multiple matching for "+rowId);
		} catch (NoResultException e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.NOT_EXISTS, "no matching for "+rowId);
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
		}
	}


}
