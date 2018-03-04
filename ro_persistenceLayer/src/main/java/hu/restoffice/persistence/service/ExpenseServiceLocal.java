/**
 *
 */
package hu.restoffice.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.restoffice.persistence.domain.Expense;
import hu.restoffice.persistence.domain.PaymentTypes;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 *
 *
 * @author kalmankostenszky
 */
@Local
public interface ExpenseServiceLocal extends PersistenceServiceLocal<Expense> {

    /**
     * @return
     */
    List<Expense> readAll() throws PersistenceServiceException;

    /**
     * @param docId
     */
    void deleteExpense(String docId) throws PersistenceServiceException;

    /**
     * @param partnerId
     * @param costCenterId
     * @param costTypeId
     * @param pm
     * @param isPayed null if filter not applied
     * @return
     */
    List<Expense> readFiltered(Integer partnerId, Integer costCenterId, Integer costTypeId, PaymentTypes pm,
            Boolean isPayed) throws PersistenceServiceException;

    /**
     * @param docId
     * @return
     */
    Expense readById(String docId) throws PersistenceServiceException;

    /**
     * @param from
     */
    void add(Expense from) throws PersistenceServiceException;

    /**
     * @param expense
     */
    void update(Expense expense) throws PersistenceServiceException;;


}
