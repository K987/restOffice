/**
 *
 */
package hu.restoffice.rest.model;

/**
 * @author kalmankostenszky
 *
 */
public class PartnerContactStub {

    private Long id;
    private String name;
    private String phoneNo;
    private String emailAddress;

    public PartnerContactStub() {

    }

    /**
     * @param id
     * @param name
     * @param phoneNo
     * @param emailAddress
     */
    public PartnerContactStub(final Long id, final String name, final String phoneNo, final String emailAddress) {
        super();
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.emailAddress = emailAddress;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PartnerContactStub [id=" + id + ", name=" + name + ", phoneNo=" + phoneNo + ", emailAddress="
                + emailAddress + "]";
    }

}
