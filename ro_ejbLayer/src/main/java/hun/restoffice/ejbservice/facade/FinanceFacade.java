/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.converter.ExpenseConverterLocal;
import hun.restoffice.ejbservice.converter.FinanceMiscConverterLocal;
import hun.restoffice.ejbservice.converter.IncomeConverterLocal;
import hun.restoffice.ejbservice.domain.CostCenterStub;
import hun.restoffice.ejbservice.domain.ExpenseStub;
import hun.restoffice.ejbservice.domain.ExpenseTypeStub;
import hun.restoffice.ejbservice.domain.IncomeStub;
import hun.restoffice.ejbservice.exception.FacadeException;
import hun.restoffice.persistence.entity.financialTransaction.PaymentMethod;
import hun.restoffice.persistence.exception.PersistenceServiceException;
import hun.restoffice.persistence.service.ExpenseServiceLocal;
import hun.restoffice.persistence.service.FinanceMiscServiceLocal;
import hun.restoffice.persistence.service.IncomeServiceLocal;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName="ejb/financeFacade")
public class FinanceFacade implements FinanceFacadeLocal {

	private static final Logger LOG = Logger.getLogger(FinanceFacade.class);
	
	@EJB
	private ExpenseConverterLocal eConverter;
	
	@EJB
	private ExpenseServiceLocal eService;
	
	@EJB
	private FinanceMiscServiceLocal fmService;
	
	@EJB
	private FinanceMiscConverterLocal fmConverter;
	
	@EJB
	private IncomeConverterLocal iConverter;
	
	@EJB
	private IncomeServiceLocal iService;
	
	
	
	
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.ExpenseFacadeLocal#getAllExpenses()
	 */
	@Override
	public List<ExpenseStub> getAllExpenses()  throws FacadeException {
		List<ExpenseStub> rtrn = new ArrayList<>();
		try {
			rtrn = this.eConverter.to(this.eService.readAll());
			return rtrn;
		} catch (PersistenceServiceException e) {
			throw new FacadeException(e.getLocalizedMessage());
		}
		
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.ExpenseFacadeLocal#deleteExpense(java.lang.String)
	 */
	@Override
	public void deleteExpense(String docId) throws FacadeException {
		try {
			this.eService.deleteExpense(docId);
		} catch (PersistenceServiceException e) {
			LOG.error(e);
			throw new FacadeException(e.getMessage());
		}
		
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#getAllCostCenters()
	 */
	@Override
	public List<CostCenterStub> getAllCostCenters() throws FacadeException {
		List<CostCenterStub> rtrn = new ArrayList<>();
		try{
			rtrn = this.fmConverter.toCostCenterStub(this.fmService.readAllCostCenter());
			return rtrn;
		} catch (PersistenceServiceException e){
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#getAllExpenseType()
	 */
	@Override
	public List<ExpenseTypeStub> getAllExpenseType() throws FacadeException {
		List<ExpenseTypeStub> rtrn = new ArrayList<>();
		try{
			rtrn = this.fmConverter.toExpenseTypeStub(this.fmService.readAllExpenseType());
			return rtrn;
		} catch (PersistenceServiceException e){
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#getExpensesMatching(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Boolean)
	 */
	@Override
	public List<ExpenseStub> getExpensesMatching(Integer partnerId, Integer costCenterId, Integer costTypeId, Integer paymentMethodOrdinal, Boolean isPayed)
			throws FacadeException {
		LOG.info("get Expense matching invoked");
		List<ExpenseStub> rtrn = new ArrayList<>();
		PaymentMethod pm = paymentMethodOrdinal == -1 ? null : PaymentMethod.values()[paymentMethodOrdinal];
		
		try{
			rtrn = this.eConverter.to(this.eService.readFiltered(partnerId, costCenterId, costTypeId, pm, isPayed));
			return rtrn;
		} catch (PersistenceServiceException e){
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#getExpenseById(java.lang.String)
	 */
	@Override
	public ExpenseStub getExpenseById(String docId) throws FacadeException {
		try{
			return this.eConverter.to(this.eService.readById(docId));
		} catch (PersistenceServiceException e){
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#addExpense(hun.restoffice.ejbservice.domain.ExpenseStub)
	 */
	@Override
	public void addExpense(ExpenseStub stub) throws FacadeException {
		try{
			this.eService.add(this.eConverter.from(stub));
		} catch (PersistenceServiceException e){
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#addIncome(hun.restoffice.remoteClient.domain.IncomeStub)
	 */
	@Override
	public void addIncome(IncomeStub stub) throws FacadeException {
		try{
			this.iService.insert(this.iConverter.from(stub));
		}catch (PersistenceServiceException e) {
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#updateExpense(hun.restoffice.ejbservice.domain.ExpenseStub)
	 */
	@Override
	public void updateExpense(ExpenseStub stub) throws FacadeException {
		try{
			this.eService.update(this.eConverter.from(stub));
		} catch (PersistenceServiceException e){
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		
	}
	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.facade.FinanceFacadeLocal#getAllIncomes()
	 */
	@Override
	public List<IncomeStub> getAllIncomes() throws FacadeException {
		List<IncomeStub> rtrn = new ArrayList<>();
		try {
			rtrn = this.iConverter.to(this.iService.readAll());
			return rtrn;
		} catch (PersistenceServiceException e) {
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

}
