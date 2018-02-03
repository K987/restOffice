/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.DailyTransactionStub;
import hun.restoffice.persistence.entity.dailyTransaction.DailyIncome;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface DailyTransactionConverterLocal {

	/**
	 * @param stubs
	 * @return
	 */
	List<DailyIncome> from(List<DailyTransactionStub> stubs);

}
