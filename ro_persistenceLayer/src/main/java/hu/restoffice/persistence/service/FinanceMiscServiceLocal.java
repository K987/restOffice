/**
 *
 */
package hu.restoffice.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.restoffice.persistence.domain.CostCategory;
import hu.restoffice.persistence.domain.CostCenterCategory;
import hu.restoffice.persistence.domain.IncomeCategory;
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
    List<CostCenterCategory> readAllCostCenter() throws PersistenceServiceException;

    /**
     * @return
     */
    List<CostCategory> readAllExpenseType() throws PersistenceServiceException;

    /**
     * @param costCenterName
     * @return
     */
    CostCenterCategory readCostCenterByName(String costCenterName) throws PersistenceServiceException;;

    /**
     * @param costTypeName
     * @return
     */
    CostCategory readExpTypeByName(String costTypeName) throws PersistenceServiceException;

    /**
     * @param incTypeName
     * @return
     */
    IncomeCategory readIncTypeByName(String incTypeName) throws PersistenceServiceException;

}
