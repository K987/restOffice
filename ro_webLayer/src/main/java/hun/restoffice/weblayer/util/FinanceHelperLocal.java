/**
 * 
 */
package hun.restoffice.weblayer.util;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.CostCenterStub;
import hun.restoffice.ejbservice.domain.ExpenseTypeStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface FinanceHelperLocal {
	
	List<ExpenseTypeStub> getExpenseTypes() throws FacadeException;
	
	List<CostCenterStub> getCostCenters() throws FacadeException;

	List<PartnerStub> getPartners() throws FacadeException;
}
