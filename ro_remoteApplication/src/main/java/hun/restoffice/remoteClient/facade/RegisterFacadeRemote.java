/**
 * 
 */
package hun.restoffice.remoteClient.facade;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import hun.restoffice.remoteClient.domain.RegisterCloseStub;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Remote
public interface RegisterFacadeRemote {

	/**
	 * @param day
	 * @throws FacadeException 
	 */
	List<RegisterCloseStub> getRegistersToClose(Calendar day) throws FacadeException;


	/**
	 * @param toClose
	 * @throws FacadeException
	 */
	void batchRegisterClose(List<RegisterCloseStub> toClose) throws FacadeException;
	

}
