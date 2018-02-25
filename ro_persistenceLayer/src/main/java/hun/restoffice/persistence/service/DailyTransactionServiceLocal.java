/**
 *
 */
package hun.restoffice.persistence.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.domain.DailyClose;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 *
 *
 * @author kalmankostenszky
 */
@Local
public interface DailyTransactionServiceLocal {

    /**
     * @param incomes
     * @throws PersistenceServiceException
     */
    void createDailyTransactionBatch(List<DailyClose> incomes) throws PersistenceServiceException;

    /**
     * @param workDay
     * @return
     */
    List<DailyClose> findByDate(Calendar workDay) throws PersistenceServiceException;;

}
