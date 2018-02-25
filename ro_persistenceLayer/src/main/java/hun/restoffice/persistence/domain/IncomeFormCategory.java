package hun.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the income_form_type database table.
 *
 */
@Entity
@Table(name = "INCOME_FORM_CATEGORY")
@NamedQuery(name = "IncomeFormCategory.findAll", query = "SELECT i FROM IncomeFormCategory i")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "INCOME_FORM_CATEGORY_INCOME_FORM_CATEGORY_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "INCOME_FORM_CATEGORY_ID", updatable = false))
public class IncomeFormCategory extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    public IncomeFormCategory() {
    }

    @Column(name = "accounted_income_flg", nullable = false)
    private boolean accountedIncome;

    @Column(name = "conversion_rate", precision = 12, scale = 2)
    private double conversionRate;

    // TODO: add column to database
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_type_cd", nullable = false)
    private PaymentTypes paymentType;

    @Column(name = "register_type_id", nullable = false)
    private RegisterCategory registerType;

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

    /**
     * @param registerType
     */
    public void setRegisterType(final RegisterCategory registerType) {
        this.registerType = registerType;
    }

    /**
     * @return the registerType
     */
    public RegisterCategory getRegisterType() {
        return registerType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IncomeFormType [accountedIncome=" + accountedIncome + ", conversionRate=" + conversionRate
                + ", paymentType=" + paymentType + ", registerType=" + registerType + "]";
    }
}