package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the expense database table.
 *
 */
@Entity
@Table(name = "expense")
@SequenceGenerator(name = "TRANSACTION_ID_GENERATOR", sequenceName = "EXPENSE_EXPENSE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "expense_id", updatable = false))
public class Expense extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to IncomeDetail
    @OneToMany(mappedBy = "expense", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ExpenseDetail> expenseDetails = new ArrayList<>();

    @Transient
    private Double grossTotal;

    public Expense() {
    }

    /**
     * @return the incomeDetails
     */
    public List<ExpenseDetail> getExpenseDetails() {
        return expenseDetails;
    }

    /**
     * @param incomeDetails
     *            the incomeDetails to set
     */
    protected void setExpenseDetails(final List<ExpenseDetail> expenseDetails) {
        this.expenseDetails = expenseDetails;
    }

    /**
     * @return the grossTotalIncome
     */
    public double getGrossTotal() {
        return grossTotal;
    }

    /**
     * @param grossTotalIncome
     *            the grossTotalIncome to set
     */
    protected void setGrossTotal(final double grossTotalIncome) {
        grossTotal = grossTotalIncome;
    }

    public ExpenseDetail addIncomeDetail(final ExpenseDetail expenseDetail) {
        getExpenseDetails().add(expenseDetail);
        expenseDetail.setExpense(this);
        updateGrossTotal(expenseDetail.getGrossAmount());
        return expenseDetail;
    }

    public ExpenseDetail removeExpenseDetail(final ExpenseDetail expenseDetail) {
        getExpenseDetails().remove(expenseDetail);
        expenseDetail.setExpense(null);
        updateGrossTotal(expenseDetail.getGrossAmount());
        return expenseDetail;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Expense [expenseDetails=" + expenseDetails + ", grossTotal=" + grossTotal + ", transaction="
                + super.toString() + "]";
    }

    /**
     * @param grossAmt
     */
    private void updateGrossTotal(final Double grossAmt) {
        if (grossTotal == null)
            grossTotal = getExpenseDetails().stream().mapToDouble(ExpenseDetail::getGrossAmount).sum();
        else if (grossAmt != null)
            grossTotal += grossAmt;
    }

    @PostLoad
    protected void updateGrossTotal() {
        updateGrossTotal(0.0d);
    }
}