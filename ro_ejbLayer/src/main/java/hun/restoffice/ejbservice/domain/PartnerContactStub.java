/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import hun.restoffice.persistence.entity.partner.PartnerContact;

/**
 * DTO for partner contact entity
 *
 * @author kalmankostenszky
 */
public class PartnerContactStub {

	private static final String NO_NAME = "No contact name available";
	private static final String NO_PHONE = "No contact phone available";
	private static final String NO_EMAIL = "No contact email available";

	private String name;
	private String phone;
	private String email;


	public PartnerContactStub(String name, String phone, String email) {
		super();
		this.name = (name == null || name == "") ? PartnerContactStub.NO_NAME : name;
		this.phone = (phone == null || phone == "") ? PartnerContactStub.NO_PHONE : phone;
		this.email = (email == null || email == "") ? PartnerContactStub.NO_EMAIL : email;
	}


	public PartnerContactStub(PartnerContact contact) {
		this(contact.getName(), contact.getPhone(), contact.getEmail());
	}


	public PartnerContactStub() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("PartnerContactStub [name=%s, phone=%s, email=%s]", name, phone, email);
	}

	// getters setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
