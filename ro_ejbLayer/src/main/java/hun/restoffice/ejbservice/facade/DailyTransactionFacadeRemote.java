/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import hun.restoffice.ejbservice.domain.DailyTransactionStub;
import hun.restoffice.ejbservice.exception.FacadeException;

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
