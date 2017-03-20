/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.ExpenseStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.persistence.entity.financialTransaction.Expense;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless
public class ExpenseConverter implements ExpenseConverterLocal {

	@EJB
	private PartnerConverterLocal pConverter;
	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.ExpenseConverterLocal#to(java.util.List)
	 */
	@Override
	public List<ExpenseStub> to(List<Expense> expenses) {
		List<ExpenseStub> rtrn = new ArrayList<>();
		for (Expense expense : expenses) {
			rtrn.add(to(expense));
		}
		return rtrn;
	}


	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.ExpenseConverterLocal#to(hun.restoffice.persistence.entity.financialTransaction.Expense)
	 */
	@Override
	public ExpenseStub to(Expense expense) {
		
		PartnerStub partner = this.pConverter.toPartner(expense.getParty());
		return new ExpenseStub(expense.getDocId(),
				expense.getDocType().ordinal(), 
				partner, 
				expense.getPayMethod().ordinal(),
				expense.getGrossTotal(),
				expense.getDescription(), 
				expense.getRegistered(), 
				expense.getExpiry(),
				expense.getPayed(),
				(expense.getAccPeriod() == null ? null : expense.getAccPeriod().getStartDate()), 
				(expense.getAccPeriod() == null ? null : expense.getAccPeriod().getEndDate()),
				expense.getCostCenter().getName(), 
				expense.getExpType().getName());
	}


	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.ExpenseConverterLocal#from(hun.restoffice.ejbservice.domain.ExpenseStub)
	 */
	@Override
	public Expense from(ExpenseStub stub) {
		
		return new Expense(
				stub.getDocId(),
				stub.getDocType().ordinal(),
				pConverter.fromPartner(stub.getIssuer()),
				stub.getPayMethod().ordinal(),
				stub.getGrossTotal(),
				stub.getDescription(),
				stub.getRegistered(),
				stub.getExpiry(),
				stub.getPayed(),
				stub.getAccPeriodStart(),
				stub.getAccPeriodEnd(),
				stub.getCostCenter(),
				stub.getCostType()
				);
		
	}

}
