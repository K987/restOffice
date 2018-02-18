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
 * The persistent class for the payment_method database table.
 *
 */
@Entity
@Table(name="payment_method")
@NamedQuery(name="PaymentMethod.findAll", query="SELECT p FROM PaymentMethod p")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "PAYMENT_METHOD_PAYMENT_METHOD_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "payment_method_id", updatable = false))
public class Obsolete_PaymentMethodType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="payment_method_type_cd", nullable=false)
    @Enumerated(EnumType.ORDINAL)
    private PaymentTypes paymentType;

    @Column(name = "mean_of_payment_flg", nullable = false)
    private Boolean meanOfPayment;

    @Column(name = "used_as_expense_flg", nullable = false)
    private Boolean usedAsExpense;

    @Column(name = "conversion_rate_amt", precision = 12, scale = 2)
    private double conversionRate;

    @Column(name = "related_deposit_box_flg", nullable = false)
    // TODO: add relation
    private DepositBox defaultDepositBox;

    public Obsolete_PaymentMethodType() {
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
     * @return the meanOfPayment
     */
    public Boolean getMeanOfPayment() {
        return meanOfPayment;
    }

    /**
     * @param meanOfPayment
     *            the meanOfPayment to set
     */
    public void setMeanOfPayment(final Boolean meanOfPayment) {
        this.meanOfPayment = meanOfPayment;
    }

    /**
     * @return the usedAsExpense
     */
    public Boolean getUsedAsExpense() {
        return usedAsExpense;
    }

    /**
     * @param usedAsExpense
     *            the usedAsExpense to set
     */
    public void setUsedAsExpense(final Boolean usedAsExpense) {
        this.usedAsExpense = usedAsExpense;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PaymentMethodType [paymentType=" + paymentType + ", meanOfPayment=" + meanOfPayment + ", usedAsExpense="
                + usedAsExpense + ", conversionRate=" + conversionRate + ", defaultDepositBox=" + defaultDepositBox
                + "]";
    }
}