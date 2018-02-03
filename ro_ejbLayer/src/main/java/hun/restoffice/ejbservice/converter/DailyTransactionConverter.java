/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.DailyTransactionStub;
import hun.restoffice.persistence.entity.dailyTransaction.DailyIncome;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Stateless
public class DailyTransactionConverter implements DailyTransactionConverterLocal{

	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.DailyTransactionConverterLocal#from(java.util.List)
	 */
	@Override
	public List<DailyIncome> from(List<DailyTransactionStub> stubs) {
		List<DailyIncome> rtrn = new ArrayList<>();
		for (DailyTransactionStub dailyTransactionStub : stubs) {
			rtrn.add(from(dailyTransactionStub));
		}
		return rtrn;
	}

	/**
	 * @param dailyTransactionStub
	 * @return
	 */
	private DailyIncome from(DailyTransactionStub stub) {
		return new DailyIncome(stub.getPos(),stub.getCash(),stub.getCard(),stub.getRowId());
	}

	
}
