package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the income database table.
 *
 */
@Entity
@Table(name = "income")
@NamedQuery(name = "Income.findAll", query = "SELECT i FROM Income i")
@SequenceGenerator(name = "TRANSACTION_ID_GENERATOR", sequenceName = "INCOME_INCOME_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "income_id", updatable = false))
public class Income extends Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to IncomeDetail
    @OneToMany(mappedBy = "income", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<IncomeDetail> incomeDetails = new ArrayList<>();

    @Transient
    private Double grossTotal;

    public Income() {
    }

    /**
     * @return the incomeDetails
     */
    public List<IncomeDetail> getIncomeDetails() {
        return incomeDetails;
    }

    /**
     * @param incomeDetails
     *            the incomeDetails to set
     */
    protected void setIncomeDetails(final List<IncomeDetail> incomeDetails) {
        this.incomeDetails = incomeDetails;
    }

    /**
     * @return the grossTotalIncome
     */
    public Double getGrossTotal() {
        return grossTotal;
    }

    public IncomeDetail addIncomeDetail(final IncomeDetail incomeDetail) {
        getIncomeDetails().add(incomeDetail);
        incomeDetail.setIncome(this);
        updateGrossTotal(incomeDetail.getGrossAmount());
        return incomeDetail;
    }

    public IncomeDetail removeIncomeDetail(final IncomeDetail incomeDetail) {
        getIncomeDetails().remove(incomeDetail);
        incomeDetail.setIncome(null);
        updateGrossTotal(incomeDetail.getGrossAmount());
        return incomeDetail;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Income [incomeDetails=" + incomeDetails + ", grossTotal=" + grossTotal + ", transaction="
                + super.toString() + "]";
    }

    /**
     * @param grossAmt
     */
    private void updateGrossTotal(final Double grossAmt) {
        if (grossTotal == null)
            grossTotal = incomeDetails.stream().mapToDouble(IncomeDetail::getGrossAmount).sum();
        else if (grossAmt != null)
            grossTotal += grossAmt;
    }

    @PostLoad
    protected void updateGrossTotal() {
        updateGrossTotal(0.0d);
    }

}