/**
 * 
 */
package hun.restoffice.weblayer.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.domain.CostCenterStub;
import hun.restoffice.ejbservice.domain.ExpenseTypeStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.ejbservice.facade.FinanceFacadeLocal;
import hun.restoffice.ejbservice.facade.PartnerFacadeLocal;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Stateless
public class FinanceHelper implements FinanceHelperLocal {

	private static final Logger LOG = Logger.getLogger(FinanceHelper.class);
	
	@EJB
	private PartnerFacadeLocal pFacade;
	
	@EJB
	private FinanceFacadeLocal fFacade;
	/* (non-Javadoc)
	 * @see hun.restoffice.weblayer.util.ServletHelperLocal#getExpenseTypes()
	 */
	@Override
	public List<ExpenseTypeStub> getExpenseTypes() throws FacadeException {
		LOG.info("FinanceHelper#getExpenseTypes invoked");
		List<ExpenseTypeStub> stubs = new ArrayList<>();
		try {
			stubs = this.fFacade.getAllExpenseType();
		} catch (FacadeException e) {
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		return stubs;
	}

	/* (non-Javadoc)
	 * @see hun.restoffice.weblayer.util.ServletHelperLocal#getCostCenters()
	 */
	@Override
	public List<CostCenterStub> getCostCenters() throws FacadeException {
		LOG.info("FinanceHelper#getCostCenters invoked");
		List<CostCenterStub> stubs = new ArrayList<>();
		try {
			stubs = this.fFacade.getAllCostCenters();
		} catch (FacadeException e) {
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		return stubs;
	}

	/* (non-Javadoc)
	 * @see hun.restoffice.weblayer.util.ServletHelperLocal#getPartners()
	 */
	@Override
	public List<PartnerStub> getPartners() throws FacadeException {
		LOG.info("FinanceHelper#getPartners invoked");
		List<PartnerStub> stubs = new ArrayList<>();
		try {
			stubs = this.pFacade.gatAllPartner(false);
		} catch (FacadeException e) {
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
		return stubs;
	}

}
