/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.converter.PartnerConverterLocal;
import hun.restoffice.ejbservice.domain.PartnerContactStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.ejbservice.exception.AdaptorException;
import hun.restoffice.ejbservice.exception.ApplicationError;
import hun.restoffice.persistence.exception.PersistenceExceptionType;
import hun.restoffice.persistence.exception.PersistenceServiceException;
import hun.restoffice.persistence.service.PartnerServiceLocal;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 * Partner business facade
 * 
 * @author kalmankostenszky
 *
 */
@Stateless(mappedName = "ejb/partnerFacade")
public class PartnerFacade implements PartnerFacadeLocal {

	private static final Logger LOG = Logger.getLogger(PartnerFacade.class);

	@EJB
	private PartnerServiceLocal pService;

	@EJB
	private PartnerConverterLocal pConverter;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#getPartnerContact( java.lang.String)
	 */
	@Override
	public PartnerContactStub getPartnerContact(String partnerName) throws AdaptorException {
		try {
			if (LOG.isDebugEnabled())
				LOG.debug("getPartnerContact invoked w/ param " + partnerName);
			final PartnerContactStub rtrn = this.pConverter.toContact(this.pService.read(partnerName));
			return rtrn;
		} catch (final PersistenceServiceException e) {
			LOG.error(e.getLocalizedMessage());
			if (e.getType().equals(PersistenceExceptionType.NOT_EXISTS)) {
				throw new AdaptorException(ApplicationError.NOT_EXISTS, e.getMessage());
			} else if (e.getType().equals(PersistenceExceptionType.AMBIGOUS_RESULT)) {
				throw new AdaptorException(ApplicationError.UNEXPECTED_RESULT, e.getMessage());
			} else {
				throw new AdaptorException(ApplicationError.UNEXPECTED, "Unexpeted error occured during execution. Param: " + partnerName);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#gatAllPartnerContact( )
	 */
	@Override
	public List<PartnerStub> gatAllPartnerContact() throws AdaptorException {
		try {
			if (LOG.isDebugEnabled())
				LOG.debug("getAllPartnerContact invoked");
			final List<PartnerStub> rtrn = this.pConverter.toContact(this.pService.readAll(false));
			return rtrn;
		} catch (final PersistenceServiceException e) {
			LOG.error(e.getLocalizedMessage());
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#deleteUnusedPartners( )
	 */
	@Override
	public List<PartnerStub> deleteUnusedPartners() throws AdaptorException {
		try {
			if (LOG.isDebugEnabled())
				LOG.debug("deleteUnusedPartners invoked");
			final List<PartnerStub> rtrn = this.pConverter.toPartner(this.pService.deleteUnused());
			return rtrn;
		} catch (PersistenceServiceException e) {
			LOG.error(e.getLocalizedMessage());
			// TODO:delete exception
			throw new AdaptorException(ApplicationError.UNEXPECTED, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#addPartner(hun.
	 * restoffice.ejbservice.domain.PartnerStub)
	 */
	@Override
	public PartnerStub addPartner(PartnerStub partner) throws AdaptorException {
		try {
			if (LOG.isDebugEnabled())
				LOG.debug("addPartner invoked w/ param: " + partner);
			return this.pConverter.toPartner(this.pService.create(this.pConverter.fromPartner(partner)));
		} catch (PersistenceServiceException e) {
			LOG.error(e.getLocalizedMessage());
			if (e.getType().equals(PersistenceExceptionType.EXISTS_ALREADY))
				throw new AdaptorException(ApplicationError.EXISTS_ALREADY, e.getMessage());
			else
				throw new AdaptorException(ApplicationError.UNEXPECTED, e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#updatePartner(hun.
	 * restoffice.ejbservice.domain.PartnerStub)
	 */
	@Override
	public PartnerStub updatePartner(PartnerStub partner) throws AdaptorException {
		try {
			if (LOG.isDebugEnabled())
				LOG.debug("updatePartner invoked w/ param: " + partner);
			return this.pConverter.toPartner(this.pService.update(this.pConverter.fromPartner(partner)));
		} catch (PersistenceServiceException e) {
			LOG.error(e.getLocalizedMessage());
			if (e.getType().equals(PersistenceExceptionType.NOT_EXISTS)) {
				throw new AdaptorException(ApplicationError.NOT_EXISTS, e.getMessage());
			} else if (e.getType().equals(PersistenceExceptionType.AMBIGOUS_RESULT)) {
				throw new AdaptorException(ApplicationError.UNEXPECTED_RESULT, e.getMessage());
			} else {
				throw new AdaptorException(ApplicationError.UNEXPECTED, "Unexpeted error occured during execution. Param: " + partner);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#gatAllPartner()
	 */
	@Override
	public List<PartnerStub> gatAllPartner(Boolean technical) throws FacadeException {
		List<PartnerStub> rtrn = new ArrayList<>();
		try {
			rtrn = this.pConverter.toPartner(this.pService.readAll(technical));
			return rtrn;
		} catch (PersistenceServiceException e) {
			LOG.error(e);
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.facade.PartnerFacadeLocal#getPartnerById(java.lang.String)
	 */
	@Override
	public PartnerStub getPartnerById(Integer id) throws FacadeException {
		try {
			return this.pConverter.toPartner(this.pService.readById(id));
		} catch (PersistenceServiceException e) {
			LOG.error(e.getLocalizedMessage());
			throw new FacadeException(e.getLocalizedMessage());
		}
	}

}
