/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.CostCenterStub;
import hun.restoffice.ejbservice.domain.ExpenseTypeStub;
import hun.restoffice.persistence.entity.financialTransaction.CostCenter;
import hun.restoffice.persistence.entity.financialTransaction.ExpType;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless
public class FinanceMiscConverter implements FinanceMiscConverterLocal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.FinanceMiscConverterLocal#to(java.util.List)
	 */
	@Override
	public List<CostCenterStub> toCostCenterStub(List<CostCenter> costCenters) {

		List<CostCenterStub> rtrn = new ArrayList<>();
		for (CostCenter costCenter : costCenters) {
			rtrn.add(toCostCenterStub(costCenter));
		}
		return rtrn;
	}

	/**
	 * @param costCenter
	 * @return
	 */
	private CostCenterStub toCostCenterStub(CostCenter costCenter) {
		return new CostCenterStub(costCenter.getId(), costCenter.getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.FinanceMiscConverterLocal#toExpenseTypeStub(java.util.List)
	 */
	@Override
	public List<ExpenseTypeStub> toExpenseTypeStub(List<ExpType> exptypes) {
		List<ExpenseTypeStub> stubs = new ArrayList<>();

		for (ExpType expType : exptypes) {
			stubs.add(toExpenseTypeStub(expType));
		}
		return stubs;
	}

	/**
	 * @param expType
	 * @return
	 */
	private ExpenseTypeStub toExpenseTypeStub(ExpType expType) {
		return new ExpenseTypeStub(expType.getId(), expType.getName(), expType.getProdRelated());
	}

}
