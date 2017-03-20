/**
 * 
 */
package hun.restoffice.remoteClient.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import hun.restoffice.remoteClient.domain.DailyTransactionStub;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Remote
public interface DailyTransactionFacadeRemote {

	/**
	 * close daily financial transactions of sales - batch
	 * 
	 * @param stubs
	 */
	void batchTransactionClose(List<DailyTransactionStub> stubs) throws FacadeException;

	void closeDay(Date day) throws FacadeException;
}
