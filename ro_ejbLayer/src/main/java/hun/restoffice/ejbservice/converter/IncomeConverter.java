/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.DocTypeStub;
import hun.restoffice.ejbservice.domain.IncomeStub;
import hun.restoffice.ejbservice.domain.PaymentMethodStub;
import hun.restoffice.persistence.entity.financialTransaction.DocumentType;
import hun.restoffice.persistence.entity.financialTransaction.Income;
import hun.restoffice.persistence.entity.financialTransaction.PaymentMethod;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName = "ejb/incomeConverter")
public class IncomeConverter implements IncomeConverterLocal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.IncomeConverterLocal#from(hun.restoffice.remoteClient.domain.IncomeStub)
	 */
	@Override
	public Income from(IncomeStub stub) {

		return new Income(stub.getDocId(), stub.getDescription(), DocumentType.values()[stub.getDocType().ordinal()], stub.getGrossTotal(),
				stub.getRegistered(), stub.getExpiry(), stub.getPayed(), stub.getAccPeriodStart(), stub.getAccPeriodEnd(), stub.getIncomeType(),
				PaymentMethod.values()[stub.getPayMethod().ordinal()], stub.getPartner());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.IncomeConverterLocal#to(java.util.List)
	 */
	@Override
	public List<IncomeStub> to(List<Income> incomes) {
		List<IncomeStub> rtrn = new ArrayList<IncomeStub>();
		for (Income income : incomes) {
			rtrn.add(to(income));
		}
		return rtrn;
	}

	private IncomeStub to(Income income){
		return new IncomeStub(income.getDocId(), 
				DocTypeStub.values()[income.getDocType().ordinal()], 
				income.getParty().getName(),
				income.getDescription(),
				income.getGrossTotal(),
				PaymentMethodStub.values()[income.getPayMethod().ordinal()], 
				income.getIncType().getName(), 
				convertToCal(income.getRegistered()),
				convertToCal(income.getPayed()),
				convertToCal(income.getExpiry()),
				convertToCal(income.getAccPeriod() == null ? null : income.getAccPeriod().getStartDate()),
				convertToCal(income.getAccPeriod() == null ? null : income.getAccPeriod().getEndDate()));
	}
	
	private Calendar convertToCal(Date date){
		if (date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

}
