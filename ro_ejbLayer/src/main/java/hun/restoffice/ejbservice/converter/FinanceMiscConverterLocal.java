/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.CostCenterStub;
import hun.restoffice.ejbservice.domain.ExpenseTypeStub;
import hun.restoffice.persistence.entity.financialTransaction.CostCenter;
import hun.restoffice.persistence.entity.financialTransaction.ExpType;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface FinanceMiscConverterLocal {

	/**
	 * @param costCenters
	 * @return
	 */
	List<CostCenterStub> toCostCenterStub(List<CostCenter> costCenters);

	/**
	 * @param readAllExpenseType
	 * @return
	 */
	List<ExpenseTypeStub> toExpenseTypeStub(List<ExpType> readAllExpenseType);


}
