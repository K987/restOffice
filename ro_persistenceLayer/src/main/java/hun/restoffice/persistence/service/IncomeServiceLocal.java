/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.entity.financialTransaction.Income;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface IncomeServiceLocal {

	/**
	 * @param income
	 * @return 
	 */
	Income insert(Income income) throws PersistenceServiceException;

	/**
	 * @param incomes
	 * @return
	 */
	List<Income> insertAll(List<Income> incomes) throws PersistenceServiceException;

	/**
	 * @return
	 */
	List<Income> readAll() throws PersistenceServiceException;;

}
