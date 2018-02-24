package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;


/**
 * The persistent class for the expense database table.
 *
 */
@SequenceGenerator(name = "TRANSACTION_ID_GENERATOR", sequenceName = "EXPENSE_EXPENSE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "income_id", updatable = false))
public class Expense extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L;


    //bi-directional many-to-one association to IncomeDetail
    @OneToMany(mappedBy = "expense", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ExpenseDetail> expenseDetails = new ArrayList<>();

    @Transient
    private BigDecimal grossTotal;

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
    public BigDecimal getGrossTotal() {
        if (grossTotal == null)
            updateGrossTotal(BigDecimal.ZERO);
        return grossTotal;
    }

    /**
     * @param grossTotalIncome
     *            the grossTotalIncome to set
     */
    protected void setGrossTotal(final BigDecimal grossTotalIncome) {
        grossTotal = grossTotalIncome;
    }

    public ExpenseDetail addIncomeDetail(final ExpenseDetail expenseDetail) {
        getExpenseDetails().add(expenseDetail);
        expenseDetail.setExpense(this);
        if (expenseDetail.getGrossAmount() != null)
            updateGrossTotal(expenseDetail.getGrossAmount());
        return expenseDetail;
    }

    public ExpenseDetail removeExpenseDetail(final ExpenseDetail expenseDetail) {
        getExpenseDetails().remove(expenseDetail);
        expenseDetail.setExpense(null);
        if (expenseDetail.getGrossAmount() != null)
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
    private void updateGrossTotal(final BigDecimal grossAmt) {
        if (grossTotal == null)
            grossTotal = getExpenseDetails().stream().map(ExpenseDetail::getGrossAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .add(grossAmt == null ? BigDecimal.ZERO : grossAmt);
        else if (grossAmt != null)
            grossTotal = grossTotal.add(grossAmt);
    }
}