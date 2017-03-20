/**
 * 
 */
package hun.restoffice.persistence.entity.partner;

import javax.persistence.*;

/**
 * The embeded class for partner contact information
 *
 * @author kalmankostenszky
 */
@Embeddable
public class PartnerContact {

	@Column(name = "partner_contact_name")
	private String name;

	@Column(name = "partner_contact_phone")
	private String phone;

	@Column(name = "partner_contact_email")
	private String email;

	public PartnerContact() {

	}

	/**
	 * @param name
	 * @param email
	 * @param phone
	 */
	public PartnerContact(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
