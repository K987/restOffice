/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hun.restoffice.persistence.entity.financialTransaction.IncType;
import hun.restoffice.persistence.entity.financialTransaction.Income;
import hun.restoffice.persistence.entity.partner.Partner;
import hun.restoffice.persistence.exception.PersistenceExceptionType;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName = "ejb/incomeService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class IncomeService implements IncomeServiceLocal {

	private static final Logger LOG = Logger.getLogger(IncomeService.class);

	@PersistenceContext(unitName = "ro-persistence-unit")
	private EntityManager entityManager;
	
	@EJB
	private PartnerServiceLocal pService;

	@EJB
	private FinanceMiscServiceLocal fmService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hun.restoffice.persistence.service.IncomeServiceLocal#add(hun.restoffice.persistence.entity.financialTransaction.
	 * Income)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Income insert(Income income) throws PersistenceServiceException {
		if (this.count(income.getDocId()) == 0) {
			Partner partner = this.pService.read(income.getPartnerName());
			IncType incType = this.fmService.readIncTypeByName(income.getIncTypeName());
			income.setParty(partner);
			income.setIncType(incType);
			try{
				return this.entityManager.merge(income);
			} catch (Exception e){
				LOG.error(e);
				throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unknown exception occured while persisting income with docId: "+income.getDocId());
			}
		}
		else{
			throw new PersistenceServiceException(PersistenceExceptionType.EXISTS_ALREADY, "income already exists w/ docId: "+income.getDocId());
		}
	}

	/**
	 * @param docId
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private int count(String docId) throws PersistenceServiceException {
		try {
			return this.entityManager.createNamedQuery(Income.COUNT_BY_ID, Long.class).setParameter(Income.ID, docId.trim().toLowerCase()).getSingleResult()
					.intValue();
		} catch (Exception e) {
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "error while counting docId: " + docId);
		}

	}

	/* (non-Javadoc)
	 * @see hun.restoffice.persistence.service.IncomeServiceLocal#insertAll(java.util.List)
	 */
	@Override
	public List<Income> insertAll(List<Income> incomes) throws PersistenceServiceException {
		List<Income> rtrn = new ArrayList<>();
		for (Income income : incomes) {
			rtrn.add(insert(income));
		}
		return rtrn;
	}

	/* (non-Javadoc)
	 * @see hun.restoffice.persistence.service.IncomeServiceLocal#readAll()
	 */
	@Override
	public List<Income> readAll() throws PersistenceServiceException {
		try{
			return this.entityManager.createNamedQuery(Income.FIND_ALL, Income.class).getResultList();
		} catch (Exception e){
			LOG.error(e);
			throw new PersistenceServiceException(PersistenceExceptionType.UNKNOWN, "unknown exception while retireving all incomes");
		}
		
	}

}
