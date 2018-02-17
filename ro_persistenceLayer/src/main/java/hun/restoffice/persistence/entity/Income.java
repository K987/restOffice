package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the income database table.
 *
 */
// TODO: payment detail ne legyen önálló entitás
@Entity
@Table(name="income")
@NamedQuery(name="Income.findAll", query="SELECT i FROM Income i")
public class Income implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="INCOME_INCOMEID_GENERATOR", sequenceName="INCOME_INCOME_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCOME_INCOMEID_GENERATOR")
    @Column(name = "income_id", updatable = false)
    private Long id;

    @Column(name="create_dttm", nullable=false)
    private LocalDateTime createdAt;

    @Column(name="deleted_dttm")
    private LocalDateTime deletedAt;

    @Column(name = "document_no", unique = true, nullable = false, length = 50)
    private String documentNo;

    //uni-directional many-to-one association to Partner
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="partner_id", nullable=false)
    private Partner partner;

    //uni-directional many-to-one association to PaymentDetail
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="payment_detail_id", nullable=false)
    private PaymentDetail paymentDetail;

    //uni-directional many-to-one association to RoUser
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="create_user_id", nullable=false)
    private User createByUser;

    //uni-directional many-to-one association to RoUser
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="delete_user_id")
    private User deleteByUser;

    //bi-directional many-to-one association to IncomeDetail
    @OneToMany(mappedBy = "income", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<IncomeDetail> incomeDetails = new ArrayList<>();

    @Transient
    private BigDecimal grossTotalIncome;

    public Income() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createDttm) {
        createdAt = createDttm;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(final LocalDateTime deletedDttm) {
        deletedAt = deletedDttm;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(final String documentNo) {
        this.documentNo = documentNo;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(final Partner partner) {
        this.partner = partner;
    }

    public PaymentDetail getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(final PaymentDetail paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public User getCreateByUser() {
        return createByUser;
    }

    public void setCreateByUser(final User createByUser) {
        this.createByUser = createByUser;
    }

    public User getDeleteByUser() {
        return deleteByUser;
    }

    public void setDeleteByUser(final User deleteByUser) {
        this.deleteByUser = deleteByUser;
    }

    public List<IncomeDetail> getIncomeDetails() {
        return incomeDetails;
    }

    public BigDecimal getGrossTotalIncome() {
        if (grossTotalIncome == null)
            updateGrossTotalIncome(BigDecimal.ZERO);
        return grossTotalIncome;
    }
    // public void setIncomeDetails(final List<IncomeDetail> incomeDetails) {
    // this.incomeDetails = incomeDetails;
    // }

    public IncomeDetail addIncomeDetail(final IncomeDetail incomeDetail) {
        getIncomeDetails().add(incomeDetail);
        incomeDetail.setIncome(this);
        if (incomeDetail.getIncomeGrossAmt() != null)
            updateGrossTotalIncome(incomeDetail.getIncomeGrossAmt());
        return incomeDetail;
    }

    public IncomeDetail removeIncomeDetail(final IncomeDetail incomeDetail) {
        getIncomeDetails().remove(incomeDetail);
        incomeDetail.setIncome(null);
        if (incomeDetail.getIncomeGrossAmt() != null)
            updateGrossTotalIncome(incomeDetail.getIncomeGrossAmt());
        return incomeDetail;
    }

    /**
     * @param incomeGrossAmt
     */
    private void updateGrossTotalIncome(final BigDecimal incomeGrossAmt) {
        if (grossTotalIncome == null)
            grossTotalIncome = incomeDetails.stream().map(IncomeDetail::getIncomeGrossAmt).reduce(BigDecimal.ZERO, BigDecimal::add).add(incomeGrossAmt == null ? BigDecimal.ZERO : incomeGrossAmt);
        else if (incomeGrossAmt != null)
            grossTotalIncome = grossTotalIncome.add(incomeGrossAmt);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Income [id=" + id + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + ", documentNo="
                + documentNo + ", partner=" + partner + ", paymentDetail=" + paymentDetail + ", createByUser="
                + createByUser + ", deleteByUser=" + deleteByUser + ", incomeDetails=" + incomeDetails
                + ", grossTotalIncome=" + grossTotalIncome + "]";
    }

}