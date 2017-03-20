/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.converter.RegisterConverterLocal;
import hun.restoffice.persistence.entity.dailyTransaction.RegisterClose;
import hun.restoffice.persistence.exception.PersistenceServiceException;
import hun.restoffice.persistence.service.RegisterServiceLocal;
import hun.restoffice.remoteClient.domain.RegisterCloseStub;
import hun.restoffice.remoteClient.exception.FacadeException;
import hun.restoffice.remoteClient.facade.RegisterFacadeRemote;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless(mappedName = "ejb/registerFacade")
public class RegisterFacade implements RegisterFacadeRemote {

	private static final Logger LOG = Logger.getLogger(RegisterFacade.class);

	@EJB
	private RegisterServiceLocal rService;

	@EJB
	private RegisterConverterLocal rConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.remoteClient.service.RegisterFacadeRemote#getRegistersToClose(java.util.Calendar)
	 */
	@Override
	public List<RegisterCloseStub> getRegistersToClose(Calendar day) throws FacadeException {
		LOG.info("getRegistersToClose invoked");
		List<RegisterCloseStub> rtrn = new ArrayList<>();
		try {
			rtrn = this.rConverter.to(this.rService.readRegisterClose(day));
			if (rtrn.isEmpty()) {
				LOG.info("register is empty");
				List<RegisterCloseStub> tmp = this.rConverter.to(this.rService.readAllWithLastClose());
				LOG.info("we have results");
				for (RegisterCloseStub registerCloseStub : tmp) {
					LOG.info(registerCloseStub);
					rtrn.add(
							new RegisterCloseStub(registerCloseStub.getRegisterStub(), new BigDecimal(0), day.getTime(), (registerCloseStub.getCloseNo() + 1)));
				}
			}
			return rtrn;
		} catch (PersistenceServiceException e) {
			LOG.error(e);
			throw new hun.restoffice.remoteClient.exception.FacadeException(e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.remoteClient.service.RegisterFacadeRemote#createRegisterClose(java.util.List)
	 */
	@Override
	public void batchRegisterClose(List<RegisterCloseStub> toClose) throws FacadeException {
		LOG.info("createRegisterClose invoked");

		List<RegisterClose> closes = new ArrayList<>();
		for (RegisterCloseStub close : toClose) {
			closes.add(new RegisterClose(close.getRegisterStub().getRegisterId(), close.getCloseAmt(), close.getCloseDate().getTime(), close.getCloseNo()));
		}
		try {
			this.rService.createBatchRegisterClose(closes);
		} catch (PersistenceServiceException e) {
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}
}
