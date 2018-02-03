/**
 * 
 */
package hun.restoffice.ejbservice.domain;

//import org.codehaus.jackson.annotate.JsonCreator;
//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.annotate.JsonProperty;

import hun.restoffice.persistence.entity.partner.Partner;

/**
 * DTO for partner entity
 *
 * @author kalmankostenszky
 */
public class PartnerStub {

	// private static final Logger LOG = Logger.getLogger(PartnerStub.class);

	private String name;
	private String account;
	private PartnerContactStub contact;
	private Integer id;
	
	public PartnerStub() {
		
	}

	public PartnerStub(final Partner partner, final boolean account) {
		this.id = partner.getId();
		this.name = partner.getName();
		if (account)
			this.account = (partner.getAccount() == null || partner.getAccount() == "") ? "No account number available" : partner.getAccount();
		else
			this.account = "XXXXXXXXXXXXXXXXXXXXXXXX";
		if (partner.getContact() != null)
			this.contact = new PartnerContactStub(partner.getContact());
		else
			this.contact = null;
	}

	public PartnerStub(String name, String account, PartnerContactStub contact) {
		super();
		this.name = name;
		this.account = account;
		this.contact = contact;
		this.id = -1;
	}

//	@JsonCreator
//	public PartnerStub(@JsonProperty("name") String name, @JsonProperty("account") String account, @JsonProperty("contact.name") String pName,
//			@JsonProperty("contact.phone") String pPhone, @JsonProperty("contact.email") String pEmail) {
//		this(name, account, new PartnerContactStub(pName, pPhone, pEmail));
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PartnerStub [name=%s, account=%s, contact=%s]", name, account, contact);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @return the contact
	 */
	public PartnerContactStub getContact() {
		return contact;
	}

//	/**
//	 * @param name
//	 *            the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}

//	/**
//	 * @param account
//	 *            the account to set
//	 */
//	public void setAccount(String account) {
//		this.account = account;
//	}

//	/**
//	 * @param contact
//	 *            the contact to set
//	 */
//	public void setContact(PartnerContactStub contact) {
//		this.contact = contact;
//	}

	/**
	 * @return the id
	 */
//	@JsonIgnore
	public Integer getId() {
		return id;
	}

}
