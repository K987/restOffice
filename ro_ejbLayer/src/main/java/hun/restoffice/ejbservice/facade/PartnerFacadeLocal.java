/**
 * 
 */
package hun.restoffice.ejbservice.facade;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.PartnerContactStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.ejbservice.exception.AdaptorException;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 * Partner business facade
 * 
 * @author kalmankostenszky
 */
@Local
public interface PartnerFacadeLocal {

	/**
	 * Returns partner contact information if exists
	 * 
	 * @param partnerName
	 *            partner company name
	 * @return
	 * @throws AdaptorException
	 */
	PartnerContactStub getPartnerContact(String partnerName) throws AdaptorException;

	/**
	 * Returns all partner contact information
	 * 
	 * @return
	 * @throws AdaptorException
	 */
	List<PartnerStub> gatAllPartnerContact() throws AdaptorException;

	/**
	 * Delete partners without financialtransactions
	 * 
	 * @return
	 */
	List<PartnerStub> deleteUnusedPartners() throws AdaptorException;

	/**
	 * Add new partner
	 * 
	 * @param partner
	 *            partner to add
	 * @return added partner data
	 * @throws AdaptorException
	 */
	PartnerStub addPartner(PartnerStub partner) throws AdaptorException;

	/**
	 * Remove existing partner
	 * 
	 * @param partner
	 *            partner to updated
	 * @return updated partner data
	 * @throws AdaptorException
	 */
	PartnerStub updatePartner(PartnerStub partner) throws AdaptorException;

	/**
	 * @return
	 */
	List<PartnerStub> gatAllPartner(Boolean technical) throws FacadeException;

	/**
	 * @param id
	 * @return
	 */
	PartnerStub getPartnerById(Integer id) throws FacadeException;

}
