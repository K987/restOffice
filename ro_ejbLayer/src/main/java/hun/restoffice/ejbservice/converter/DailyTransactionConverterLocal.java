/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.entity.dailyTransaction.DailyIncome;
import hun.restoffice.remoteClient.domain.DailyTransactionStub;

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
