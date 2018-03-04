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
 * The persistent class for the income_detail database table.
 *
 */
@Entity
@Table(name = "income_detail")
@NamedQuery(name = "IncomeDetail.findAll", query = "SELECT i FROM IncomeDetail i")
@SequenceGenerator(name = "TRANSACTION_DETAIL_ID_GENERATOR", sequenceName = "INCOME_DETAIL_INCOME_DETAIL_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "income_detail_id", updatable = false))
public class IncomeDetail extends TransactionDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to Income
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_id", nullable = false)
    private Income income;

    // uni-directional many-to-one association to IncomeType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INCOME_CATEGORY_ID", nullable = false)
    private IncomeCategory incomeType;

    public IncomeDetail() {
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(final Income income) {
        this.income = income;
    }

    public IncomeCategory getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(final IncomeCategory incomeType) {
        this.incomeType = incomeType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IncomeDetail [income=" + income + ", incomeType=" + incomeType + ", transactionDetail="
                + super.toString() + "]";
    }

}