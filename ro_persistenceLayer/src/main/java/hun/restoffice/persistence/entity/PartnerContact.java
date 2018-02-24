package hun.restoffice.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Embeddable
public class PartnerContact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PARTNER_CONTACT_PARTNERCONTACTID_GENERATOR", sequenceName = "PARTNER_CONTACT_PARTNER_CONTACT_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_CONTACT_PARTNERCONTACTID_GENERATOR")
    @Column(name = "partner_contact_id", updatable = false)
    private Long id;

    @Column(name = "cell_phone_no", length = 50)
    private String cellPhoneNo;

    @Column(name = "contact_person_name", nullable = false, length = 50)
    private String contactPersonName;

    @Column(name = "email_addr", length = 253)
    private String emailAddress;

    @Column(name = "phone_no", length = 50)
    private String phoneNo;

    public PartnerContact() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(final String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(final String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PartnerContact [cellPhoneNo=" + cellPhoneNo + ", contactPersonName=" + contactPersonName
                + ", emailAddres=" + emailAddress + ", phoneNo=" + phoneNo + "]";
    }

}