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
 * The persistent class for the expense database table.
 *
 */
// TODO: payment detail ne legyen entitás
@Entity
@Table(name="expense")
@NamedQuery(name="Expense.findAll", query="SELECT e FROM Expense e")
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EXPENSE_EXPENSEID_GENERATOR", sequenceName="EXPENSE_EXPENSE_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSE_EXPENSEID_GENERATOR")
    @Column(name = "expense_id", updatable = false)
    private Long id;

    @Column(name="create_dttm", nullable=false)
    private LocalDateTime createdAt;

    @Column(name="delete_dttm")
    private LocalDateTime deletedAt;

    @Column(name = "document_no", nullable = false, length = 50, unique = true)
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
    private User createdBy;

    //uni-directional many-to-one association to RoUser
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="delete_user_id")
    private User deletedBy;

    //bi-directional many-to-one association to ExpenseDetail
    @OneToMany(mappedBy = "expense", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ExpenseDetail> expenseDetails = new ArrayList<>();

    @Transient
    private BigDecimal grossTotalExpense;

    public Expense() {
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

    public void setDeleteDttm(final LocalDateTime deleteDttm) {
        deletedAt = deleteDttm;
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

    public User getCreateBy() {
        return createdBy;
    }

    public void setCreateBy(final User createByUser) {
        createdBy = createByUser;
    }

    public User getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(final User deleteByUser) {
        deletedBy = deleteByUser;
    }

    public List<ExpenseDetail> getExpenseDetails() {
        return expenseDetails;
    }

    // public void setExpenseDetails(final List<ExpenseDetail> expenseDetails) {
    // this.expenseDetails = expenseDetails;
    // }

    public ExpenseDetail addExpenseDetail(final ExpenseDetail expenseDetail) {
        getExpenseDetails().add(expenseDetail);
        expenseDetail.setExpense(this);
        if (expenseDetail.getExpenseGrossAmt() != null)
            updateTotalGrossExpense(expenseDetail.getExpenseGrossAmt());
        return expenseDetail;
    }


    public ExpenseDetail removeExpenseDetail(final ExpenseDetail expenseDetail) {
        getExpenseDetails().remove(expenseDetail);
        if (expenseDetail.getExpenseGrossAmt() != null)
            updateTotalGrossExpense(expenseDetail.getExpenseGrossAmt().negate());
        expenseDetail.setExpense(null);

        return expenseDetail;
    }

    public BigDecimal getGrossTotalExpense() {
        if (grossTotalExpense == null)
            updateTotalGrossExpense(BigDecimal.ZERO);
        return grossTotalExpense;
    }

    /**
     * @param expenseGrossAmt
     */
    private void updateTotalGrossExpense(final BigDecimal expenseGrossAmt) {
        if (grossTotalExpense == null)
            grossTotalExpense = expenseDetails.stream().map(ExpenseDetail::getExpenseGrossAmt).reduce(BigDecimal.ZERO,
                    BigDecimal::add).add(expenseGrossAmt == null ? BigDecimal.ZERO : expenseGrossAmt);
        else if (expenseGrossAmt != null)
            grossTotalExpense = grossTotalExpense.add(expenseGrossAmt);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Expense [id=" + id + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + ", documentNo="
                + documentNo + ", partner=" + partner + ", paymentDetail=" + paymentDetail + ", createdBy=" + createdBy
                + ", deletedBy=" + deletedBy + ", expenseDetails=" + expenseDetails + ", grossTotalExpense="
                + grossTotalExpense + "]";
    }

}