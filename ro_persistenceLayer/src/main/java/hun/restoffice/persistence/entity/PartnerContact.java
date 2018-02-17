package hun.restoffice.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the partner_contact database table.
 *
 */
@Entity
@Table(name="partner_contact")
@NamedQuery(name="PartnerContact.findAll", query="SELECT p FROM PartnerContact p")
public class PartnerContact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PARTNER_CONTACT_PARTNERCONTACTID_GENERATOR", sequenceName="PARTNER_CONTACT_PARTNER_CONTACT_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARTNER_CONTACT_PARTNERCONTACTID_GENERATOR")
    @Column(name = "partner_contact_id", updatable = false)
    private Long id;

    @Column(name="cell_phone_no", length=50)
    private String cellPhoneNo;

    @Column(name="contact_person_name", nullable=false, length=50)
    private String contactPersonName;

    @Column(name="email_addr", length=253)
    private String emailAddr;

    @Column(name="phone_no", length=50)
    private String phoneNo;

    //bi-directional many-to-one association to Partner
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="partner_id", nullable=false)
    private Partner partner;

    public PartnerContact() {
    }

    public Long getId() {
        return id;
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

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(final String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(final Partner partner) {
        this.partner = partner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PartnerContact [id=" + id + ", cellPhoneNo=" + cellPhoneNo + ", contactPersonName=" + contactPersonName
                + ", emailAddr=" + emailAddr + ", phoneNo=" + phoneNo + ", partner=" + partner + "]";
    }

}