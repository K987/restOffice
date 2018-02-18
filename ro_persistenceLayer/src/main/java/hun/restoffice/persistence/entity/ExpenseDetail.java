package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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

import hun.restoffice.persistence.type.CostCenterType;
import hun.restoffice.persistence.type.CostCategoryType;


/**
 * The persistent class for the expense_detail database table.
 *
 */
// TODO: getterek, setterek láthatóságá átgondolni
@Entity
@Table(name="expense_detail")
@NamedQuery(name="ExpenseDetail.findAll", query="SELECT e FROM ExpenseDetail e")
public class ExpenseDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EXPENSE_DETAIL_EXPENSEDETAILID_GENERATOR", sequenceName="EXPENSE_DETAIL_EXPENSE_DETAIL_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSE_DETAIL_EXPENSEDETAILID_GENERATOR")
    @Column(name = "expense_detail_id", updatable = false)
    private Long id;

    @Column(name="description_txt", nullable=false, length=500)
    private String description;

    @Column(name="expense_gross_amt", nullable=false, precision=12, scale=2)
    private BigDecimal expenseGrossAmt;

    @Column(name="expense_net_amt", precision=12, scale=2)
    private BigDecimal expenseNetAmt;

    @Column(name="expense_vat_pct", precision=2, scale=2)
    private BigDecimal expenseVatPct;

    //uni-directional many-to-one association to CostCenter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cost_center_id", nullable=false)
    private CostCenterType costCenter;

    //uni-directional many-to-one association to CostType
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cost_type_id", nullable=false)
    private CostCategoryType costType;

    //bi-directional many-to-one association to Expense
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="expense_id", nullable=false)
    private Expense expense;

    public ExpenseDetail() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String descriptionTxt) {
        description = descriptionTxt;
    }

    public BigDecimal getExpenseGrossAmt() {
        return expenseGrossAmt;
    }

    public void setExpenseGrossAmt(final BigDecimal expenseGrossAmt) {
        this.expenseGrossAmt = expenseGrossAmt;
    }

    public BigDecimal getExpenseNetAmt() {
        return expenseNetAmt;
    }

    public void setExpenseNetAmt(final BigDecimal expenseNetAmt) {
        this.expenseNetAmt = expenseNetAmt;
    }

    public BigDecimal getExpenseVatPct() {
        return expenseVatPct;
    }

    public void setExpenseVatPct(final BigDecimal expenseVatPct) {
        this.expenseVatPct = expenseVatPct;
    }

    public CostCenterType getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(final CostCenterType costCenter) {
        this.costCenter = costCenter;
    }

    public CostCategoryType getCostType() {
        return costType;
    }

    public void setCostType(final CostCategoryType costType) {
        this.costType = costType;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(final Expense expense) {
        this.expense = expense;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ExpenseDetail [id=" + id + ", description=" + description + ", expenseGrossAmt=" + expenseGrossAmt
                + ", expenseNetAmt=" + expenseNetAmt + ", expenseVatPct=" + expenseVatPct + ", costCenter=" + costCenter
                + ", costType=" + costType + ", expense=" + expense + "]";
    }

}