/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.entity.financialTransaction.Income;
import hun.restoffice.remoteClient.domain.IncomeStub;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface IncomeConverterLocal {

	/**
	 * @param stub
	 * @return
	 */
	Income from(IncomeStub stub);

	/**
	 * @param incomes
	 * @return
	 */
	List<IncomeStub> to(List<Income> incomes);

}
