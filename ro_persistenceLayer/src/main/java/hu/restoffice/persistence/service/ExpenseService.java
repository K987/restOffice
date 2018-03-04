package hu.restoffice.persistence.service;

import java.util.List;

import hu.restoffice.persistence.domain.Expense;
import hu.restoffice.persistence.domain.PaymentTypes;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 *
 */
public class ExpenseService extends PersistenceService<Expense> implements ExpenseServiceLocal {

    /*
     * (non-Javadoc)
     * 
     * @see hun.restoffice.persistence.service.ExpenseServiceLocal#readAll()
     */
    @Override
    public List<Expense> readAll() throws PersistenceServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hun.restoffice.persistence.service.ExpenseServiceLocal#deleteExpense(java.
     * lang.String)
     */
    @Override
    public void deleteExpense(final String docId) throws PersistenceServiceException {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hun.restoffice.persistence.service.ExpenseServiceLocal#readFiltered(java.lang
     * .Integer, java.lang.Integer, java.lang.Integer,
     * hun.restoffice.persistence.domain.PaymentTypes, java.lang.Boolean)
     */
    @Override
    public List<Expense> readFiltered(final Integer partnerId, final Integer costCenterId, final Integer costTypeId,
            final PaymentTypes pm, final Boolean isPayed) throws PersistenceServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hun.restoffice.persistence.service.ExpenseServiceLocal#readById(java.lang.
     * String)
     */
    @Override
    public Expense readById(final String docId) throws PersistenceServiceException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hun.restoffice.persistence.service.ExpenseServiceLocal#add(hun.restoffice.
     * persistence.domain.Expense)
     */
    @Override
    public void add(final Expense from) throws PersistenceServiceException {
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hun.restoffice.persistence.service.ExpenseServiceLocal#update(hun.restoffice.
     * persistence.domain.Expense)
     */
    @Override
    public void update(final Expense expense) throws PersistenceServiceException {
        // TODO Auto-generated method stub
    }

}
