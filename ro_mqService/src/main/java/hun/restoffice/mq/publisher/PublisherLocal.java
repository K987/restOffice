/**
 * 
 */
package hun.restoffice.mq.publisher;

import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.remoteClient.domain.IncomeStub;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Stateless
public interface PublisherLocal {

	/**
	 * @param incomes
	 */
	void publish(List<IncomeStub> incomes);

}
