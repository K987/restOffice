/**
 * 
 */
package hun.restoffice.ejbservice.business;

import java.util.Calendar;

import javax.ejb.Local;

import hun.restoffice.remoteClient.exception.FacadeException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface DailyTransactionBusinessLocal {

	void closeWorkDay(Calendar workDay) throws FacadeException;
}
