/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.entity.dailyTransaction.Register;
import hun.restoffice.persistence.entity.dailyTransaction.RegisterClose;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * Register persistence interface
 *
 * @author kalmankostenszky
 */
@Local
public interface RegisterServiceLocal {

	/**
	 * Read RegisterCloses on a given day
	 * @param day
	 * @return
	 */
	List<RegisterClose> readRegisterClose(Calendar day) throws PersistenceServiceException;

	/**
	 * read RegisterCloses to all registers with the last close
	 * 
	 * @return
	 */
	List<RegisterClose> readAllWithLastClose() throws PersistenceServiceException;

	/**
	 * read Register with Id
	 * @param id
	 * @return
	 */
	Register readRegisterWithId(String id) throws PersistenceServiceException;


	/**
	 * Create new register close
	 * @param closes
	 * @throws PersistenceServiceException
	 */
	void createBatchRegisterClose(List<RegisterClose> closes) throws PersistenceServiceException;


}
