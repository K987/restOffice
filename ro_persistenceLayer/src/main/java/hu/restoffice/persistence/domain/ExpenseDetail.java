package hu.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the expense_detail database table.
 *
 */
// TODO: getterek, setterek láthatóságá átgondolni
@Entity
@Table(name = "expense_detail")
@NamedQuery(name = "ExpenseDetail.findAll", query = "SELECT e FROM ExpenseDetail e")
@SequenceGenerator(name = "TRANSACTION_DETAIL_ID_GENERATOR", sequenceName = "EXPENSE_DETAIL_EXPENSE_DETAIL_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "expense_detail_id", updatable = false))
public class ExpenseDetail extends TransactionDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    // uni-directional many-to-one association to CostCenter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_center_category_id", nullable = false)
    private CostCenterCategory costCenter;

    // uni-directional many-to-one association to CostType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cost_category_id", nullable = false)
    private CostCategory costType;

    // bi-directional many-to-one association to Expense
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    public ExpenseDetail() {
    }

    public CostCenterCategory getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(final CostCenterCategory costCenter) {
        this.costCenter = costCenter;
    }

    public CostCategory getCostType() {
        return costType;
    }

    public void setCostType(final CostCategory costType) {
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
        return "ExpenseDetail [costCenter=" + costCenter + ", costType=" + costType + ", expense=" + expense
                + ", transactionDetail=" + super.toString() + "]";
    }

}