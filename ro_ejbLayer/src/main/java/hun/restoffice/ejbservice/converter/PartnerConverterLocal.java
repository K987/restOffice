/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.PartnerContactStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.persistence.entity.partner.Partner;

/**
 * Convert partner entity and stub back and forth
 * 
 * @author kalmankostenszky
 */
@Local
public interface PartnerConverterLocal {

	/**
	 * Convert partner entity to contact stub
	 * 
	 * @param partner
	 * @return
	 */
	PartnerContactStub toContact(Partner partner);

	/**
	 * Convert partner entities to contact stubs
	 * 
	 * @param partners
	 * @return
	 */
	List<PartnerStub> toContact(List<Partner> partners);

	/**
	 * Convert partner entites of partner stubs
	 * 
	 * @param partners
	 * @return
	 */
	List<PartnerStub> toPartner(List<Partner> partners);

	/**
	 * Convert partner entity of partner stub
	 * 
	 * @param partners
	 * @return
	 */
	PartnerStub toPartner(Partner partner);

	/**
	 * Convert partner stub to partner entity
	 * 
	 * @param partner
	 * @return
	 */
	Partner fromPartner(PartnerStub partner);

}
