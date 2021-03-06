/**
 *
 */
package hu.restoffice.persistence.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import hu.restoffice.persistence.domain.Employee;
import hu.restoffice.persistence.domain.EmployeeShift;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * Employee persistence interface
 *
 * @author kalmankostenszky
 */
@Local
public interface EmployeeServiceLocal {

    /**
     * Retrieve all employees
     *
     * @return
     * @throws PersistenceServiceException
     */
    List<Employee> readAll() throws PersistenceServiceException;

    /**
     * Create new employee
     *
     * @param employee
     * @return
     * @throws PersistenceServiceException
     */
    public Employee create(Employee employee) throws PersistenceServiceException;

    /**
     * Queries employee work schedule betwee two dates
     *
     * @param name
     * @param from
     * @param to
     * @return
     * @throws PersistenceServiceException
     */
    Employee queryEmpSchedule(String name, Calendar from, Calendar to) throws PersistenceServiceException;

    /**
     * Deletes employee
     *
     * @param employeeName
     * @return
     * @throws PersistenceServiceException
     */
    List<EmployeeShift> deleteEmployee(String employeeName) throws PersistenceServiceException;

    /**
     * Updates employee data
     *
     * @param employee
     * @return
     * @throws PersistenceServiceException
     */
    Employee updateEmployee(Employee employee) throws PersistenceServiceException;

}
