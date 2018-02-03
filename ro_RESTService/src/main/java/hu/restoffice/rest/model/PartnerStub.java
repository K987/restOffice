/**
 *
 */
package hu.restoffice.rest.model;

import java.util.Collection;

/**
 * @author kalmankostenszky
 *
 */
public class PartnerStub {

    private Long id;
    private String legalName;
    private String shortName;
    private String bankAccountNo;
    private String address;
    private String taxNo;
    private Collection<PartnerContactStub> contacts;

    public PartnerStub() {

    }

    /**
     * @param id
     * @param legalName
     * @param shortName
     * @param bankAccountNo
     * @param address
     * @param taxNo
     * @param contacts
     */
    public PartnerStub(final Long id, final String legalName, final String shortName, final String bankAccountNo,
            final String address, final String taxNo, final Collection<PartnerContactStub> contacts) {
        super();
        this.id = id;
        this.legalName = legalName;
        this.shortName = shortName;
        this.bankAccountNo = bankAccountNo;
        this.address = address;
        this.taxNo = taxNo;
        this.contacts = contacts;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the legalName
     */
    public String getLegalName() {
        return legalName;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @return the bankAccountNo
     */
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the taxNo
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * @return the contacts
     */
    public Collection<PartnerContactStub> getContacts() {
        return contacts;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PartnerStub [id=" + id + ", legalName=" + legalName + ", shortName=" + shortName + ", bankAccountNo="
                + bankAccountNo + ", address=" + address + ", taxNo=" + taxNo + ", contacts=" + contacts + "]";
    }

}
