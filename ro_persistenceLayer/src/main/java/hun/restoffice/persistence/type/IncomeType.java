package hun.restoffice.persistence.type;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.entity.DepositBox;
import hun.restoffice.persistence.util.ParameterType;

/**
 * The persistent class for the income_form_type database table.
 *
 */
@Entity
@Table(name = "income_form_type")
@NamedQuery(name = "IncomeFormType.findAll", query = "SELECT i FROM IncomeFormType i")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "INCOME_FORM_TYPE_INCOME_FORM_TYPE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "income_form_type_id", updatable = false))
public class IncomeType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    public IncomeType() {
    }

    // TODO: add as column to database
    private boolean accountedIncome;

    // TODO: add as column to database
    private double conversionRate;

    // TODO: add as column to database
    // join it to DepostiBox
    private DepositBox defaultDepositBox;

    // TODO: add column to database
    @Enumerated(EnumType.ORDINAL)
    private PaymentTypes paymentType;
    /**
     * @return the income
     */
    public boolean isAccountedIncome() {
        return accountedIncome;
    }

    /**
     * @param income
     *            the income to set
     */
    public void setAccountedIncome(final boolean accounted) {
        accountedIncome = accounted;
    }

    /**
     * @return the conversionRate
     */
    public double getConversionRate() {
        return conversionRate;
    }

    /**
     * @param conversionRate
     *            the conversionRate to set
     */
    public void setConversionRate(final double conversionRate) {
        this.conversionRate = conversionRate;
    }

    /**
     * @return the defaultDepositBox
     */
    public DepositBox getDefaultDepositBox() {
        return defaultDepositBox;
    }

    /**
     * @param defaultDepositBox
     *            the defaultDepositBox to set
     */
    public void setDefaultDepositBox(final DepositBox defaultDepositBox) {
        this.defaultDepositBox = defaultDepositBox;
    }

    /**
     * @return the paymentType
     */
    public PaymentTypes getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType
     *            the paymentType to set
     */
    public void setPaymentType(final PaymentTypes paymentType) {
        this.paymentType = paymentType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MeanOfIncome [accountedIncome=" + accountedIncome + ", conversionRate=" + conversionRate
                + ", defaultDepositBox=" + defaultDepositBox
                + ", paymentType=" + paymentType + "]";
    }

}