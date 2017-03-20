/**
 * 
 */
package hun.restoffice.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.persistence.entity.financialTransaction.CostCenter;
import hun.restoffice.persistence.entity.financialTransaction.ExpType;
import hun.restoffice.persistence.entity.financialTransaction.IncType;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface FinanceMiscServiceLocal {

	/**
	 * @return
	 */
	List<CostCenter> readAllCostCenter() throws PersistenceServiceException;

	/**
	 * @return
	 */
	List<ExpType> readAllExpenseType() throws PersistenceServiceException;

	/**
	 * @param costCenterName
	 * @return
	 */
	CostCenter readCostCenterByName(String costCenterName) throws PersistenceServiceException;;

	/**
	 * @param costTypeName
	 * @return
	 */
	ExpType readExpTypeByName(String costTypeName) throws PersistenceServiceException;

	/**
	 * @param incTypeName
	 * @return
	 */
	IncType readIncTypeByName(String incTypeName) throws PersistenceServiceException;

}
