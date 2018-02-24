package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@MappedSuperclass
public class TransactionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_DETAIL_ID_GENERATOR")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description_txt", nullable = false, length = 500)
    private String description;

    @Column(name = "gross_amt", nullable = false, precision = 12, scale = 2)
    private BigDecimal grossAmount;

    @Column(name = "net_amt", precision = 12, scale = 2)
    private BigDecimal netAmount;

    @Column(name = "vat_pct", precision = 2, scale = 2)
    private BigDecimal vatPercent;

    @Embedded
    private UserAction action;

    public TransactionDetail() {
        super();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the grossAmount
     */
    public BigDecimal getGrossAmount() {
        return grossAmount;
    }

    /**
     * @param grossAmount
     *            the grossAmount to set
     */
    public void setGrossAmount(final BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    /**
     * @return the netAmount
     */
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * @param netAmount
     *            the netAmount to set
     */
    public void setNetAmount(final BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    /**
     * @return the vatPercent
     */
    public BigDecimal getVatPercent() {
        return vatPercent;
    }

    /**
     * @param vatPercent
     *            the vatPercent to set
     */
    public void setVatPercent(final BigDecimal vatPercent) {
        this.vatPercent = vatPercent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TransactionDetail [id=" + id + ", description=" + description + ", grossAmount=" + grossAmount
                + ", netAmount=" + netAmount + ", vatPercent=" + vatPercent + ", action=" + action + "]";
    }

}
