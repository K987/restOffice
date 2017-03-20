/**
 * 
 */
package hun.restoffice.persistence.service;

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

import hun.restoffice.persistence.entity.financialTransaction.CostCenter;
import hun.restoffice.persistence.entity.financialTransaction.ExpType;
import hun.restoffice.persistence.entity.financialTransaction.Expense;
import hun.restoffice.persistence.entity.financialTransaction.PaymentMethod;
import hun.restoffice.persistence.exception.PersistenceExceptionType;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName = "ejb/expenseService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ExpenseService implements ExpenseServiceLocal {

	private static final Logger LOG = Logger.getLogger(ExpenseService.class);

	@PersistenceContext(unitName = "ro-persistence-unit")
	private EntityManager entityManager;

	@EJB
	private FinanceMiscServiceLocal fService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.ExpenseServiceLocal#readAll()
	 */
	@Override
	public List<Expense> readAll() throws PersistenceServiceException {
		try {

			return this.entityManager.createNamedQuery(Expense.FIND_ALL, Expense.class).getResultList();
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unknown exception while retrieving all expenses");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.ExpenseServiceLocal#deleteExpense(java.lang.String)
	 */
	@Override
	public void deleteExpense(String docId) throws PersistenceServiceException {
		Expense toDelete = readById(docId);
		try {
			this.entityManager.remove(toDelete);
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unknown error while deleting docId: " + docId);
		}

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Expense readById(String docId) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(Expense.READ_BY_DOC_ID, Expense.class).setParameter(Expense.DOC_ID, docId.trim().toLowerCase())
					.getSingleResult();
		} catch (AmbiguousResolutionException e) {
			LOG.error(e.getMessage());
			throw new PersistenceServiceException(PersistenceExceptionType.AMBIGOUS_RESULT, "multiple matching for docId: " + docId);
		} catch (NoResultException e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.NOT_EXISTS, "no matching for docId: " + docId);
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unkonow error during querying for docId: " + docId);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.persistence.service.ExpenseServiceLocal#readFiltered(java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, hun.restoffice.persistence.entity.financialTransaction.PaymentMethod, java.lang.Boolean)
	 */
	@Override
	public List<Expense> readFiltered(Integer partnerId, Integer costCenterId, Integer costTypeId, PaymentMethod pm, Boolean isPayed)
			throws PersistenceServiceException {
		LOG.info("readFiltered invoked with params: [ partnerID: " + partnerId + ", " + "costCenterId: " + costCenterId + ", " + "costTypeId: " + costTypeId
				+ ", " + "PaymentMethod: " + pm + ", " + "isPayed: " + isPayed + "]");
		int payed = isPayed == null ? 0 : isPayed.booleanValue() == true ? 1 : -1;
		int payMethod = pm == null ? -1 : pm.ordinal();

		try {
			return this.entityManager.createNamedQuery(Expense.READ_FILTERED, Expense.class).setParameter(Expense.PARTNER_ID, partnerId)
					.setParameter(Expense.COSTCENTER_ID, costCenterId).setParameter(Expense.COSTTYPE_ID, costTypeId)
					.setParameter(Expense.PAYMENT_METHOD, payMethod).setParameter(Expense.IS_PAYED, payed).getResultList();
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "error occured while reading expenses");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hun.restoffice.persistence.service.ExpenseServiceLocal#add(hun.restoffice.persistence.entity.financialTransaction
	 * .Expense)
	 */
	@Override
	public void add(Expense expense) throws PersistenceServiceException {
		CostCenter cc = this.fService.readCostCenterByName(expense.getCostCenterName());
		ExpType ep = this.fService.readExpTypeByName(expense.getCostTypeName());
		expense.setCostCenter(cc);
		expense.setExpType(ep);
		try {
			this.entityManager.persist(expense);
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, e.getLocalizedMessage());
		}
	}

	/**
	 * @param expense
	 * @throws PersistenceServiceException
	 */
	@Override
	public void update(Expense expense) throws PersistenceServiceException {
		Expense entity = this.readById(expense.getDocId());
		CostCenter cc = this.fService.readCostCenterByName(expense.getCostCenterName());
		ExpType ep = this.fService.readExpTypeByName(expense.getCostTypeName());
		expense.setCostCenter(cc);
		expense.setExpType(ep);
		entity.update(expense);
		try {
			this.entityManager.flush();
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "error while updateing expense w/ docId: " + expense.getDocId());
		}
	}

	/**
	 * @param docId
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private int count(String docId) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(Expense.COUNT, Long.class).setParameter(Expense.DOC_ID, docId.trim().toLowerCase()).getSingleResult()
					.intValue();
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "error while counting docId: " + docId);
		}
	}

}
