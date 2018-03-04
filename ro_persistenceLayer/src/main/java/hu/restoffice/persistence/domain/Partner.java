package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the partner database table.
 *
 */
@Entity
@Table(name = "partner")
@NamedQuery(name = "Partner.findAll", query = "SELECT p FROM Partner p")
public class Partner implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PARTNER_PARTNERID_GENERATOR", sequenceName = "PARTNER_PARTNER_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_PARTNERID_GENERATOR")
    @Column(name = "partner_id", updatable = false)
    private Long id;

    @Column(name = "bank_account_no", length = 30)
    private String bankAccountNo;

    @Column(name = "legal_address", length = 200)
    private String legalAddress;

    @Column(name = "legal_name", nullable = false, length = 100)
    private String legalName;

    @Column(name = "short_name", length = 50)
    private String shortName;

    @Column(name = "tax_id_no", length = 50)
    private String taxId;

    @Embedded
    private Effectivity effectivity;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "partner_contact", joinColumns = @JoinColumn(name = "partner_id"))
    private List<PartnerContact> partnerContacts = new ArrayList<>();

    public Partner() {
    }

    public Long getId() {
        return id;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(final String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(final String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(final String legalName) {
        this.legalName = legalName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(final String taxId) {
        this.taxId = taxId;
    }

    public List<PartnerContact> getPartnerContacts() {
        return partnerContacts;
    }

    public Effectivity getEffectivity() {
        return effectivity;
    }

    protected void setPartnerContacts(final List<PartnerContact> partnerContacts) {
        this.partnerContacts = partnerContacts;
    }

    public PartnerContact addPartnerContact(final PartnerContact partnerContact) {
        getPartnerContacts().add(partnerContact);

        return partnerContact;
    }

    public PartnerContact removePartnerContact(final PartnerContact partnerContact) {
        getPartnerContacts().remove(partnerContact);

        return partnerContact;
    }


    /**
     * @param effectivity
     *            the effectivity to set
     */
    protected void setEffectivity(final Effectivity effectivity) {
        this.effectivity = effectivity;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Partner [id=" + id + ", bankAccountNo=" + bankAccountNo + ", legalAddress=" + legalAddress
                + ", legalName=" + legalName + ", shortName=" + shortName + ", taxIdNo=" + taxId + ", effectivity="
                + effectivity + ", partnerContacts=" + partnerContacts + "]";
    }
}