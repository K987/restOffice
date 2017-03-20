/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.PartnerContactStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.persistence.entity.partner.Partner;

/**
 * Convert partner entity and stub back and forth
 * 
 * @author kalmankostenszky
 *
 */
@Stateless
public class PartnerConverter implements PartnerConverterLocal {

	//private static final Logger LOG = Logger.getLogger(PartnerConverter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.PartnerConverterLocal#toContact(hun.
	 * restoffice.persistence.entity.partner.Partner)
	 */
	@Override
	public PartnerContactStub toContact(Partner partner) {
		return new PartnerContactStub(partner.getContact());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.PartnerConverterLocal#toContact(java. util.List)
	 */
	@Override
	public List<PartnerStub> toContact(List<Partner> readAll) {
		List<PartnerStub> rtrn = new ArrayList<>();
		for (Partner p : readAll) {
			rtrn.add(new PartnerStub(p, false));
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.PartnerConverterLocal#toPartner(java. util.List)
	 */
	@Override
	public List<PartnerStub> toPartner(List<Partner> partners) {
		List<PartnerStub> rtrn = new ArrayList<>();
		for (Partner p : partners) {
			rtrn.add(new PartnerStub(p, true));
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.PartnerConverterLocal#toPartner(hun.
	 * restoffice.persistence.entity.partner.Partner)
	 */
	@Override
	public PartnerStub toPartner(Partner partner) {
		return new PartnerStub(partner, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.PartnerConverterLocal#fromPartner(hun
	 * .restoffice.ejbservice.domain.PartnerStub)
	 */
	@Override
	public Partner fromPartner(PartnerStub partner) {
		return new Partner(partner.getId(), partner.getName(), partner.getAccount(), false, partner.getContact().getName(), partner.getContact().getEmail(),
				partner.getContact().getPhone());
	}


}
