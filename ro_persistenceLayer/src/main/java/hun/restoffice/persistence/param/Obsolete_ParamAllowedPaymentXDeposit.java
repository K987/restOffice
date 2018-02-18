package hun.restoffice.persistence.param;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.entity.DepositBox;
import hun.restoffice.persistence.util.ParameterType;
import hun.restoffice.persistence.util.TemporalValidity;


/**
 * The persistent class for the param_allowed_payment_x_deposit database table.
 *
 */
@Entity
@Table(name="param_allowed_payment_x_deposit")
@NamedQuery(name="ParamAllowedPaymentXDeposit.findAll", query="SELECT p FROM ParamAllowedPaymentXDeposit p")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "PARAM_ALLOWED_PAYMENT_X_DEPOSIT_ALLOWED_PAYMENT_X_DEPOSIT_BOX_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "deposit_box_id", updatable = false))
public class Obsolete_ParamAllowedPaymentXDeposit extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="default_deposit_flg", nullable=false)
    private Boolean defaultDeposit;

    @Column(name="deposit_box_id", nullable=false)
    @OneToOne()
    private DepositBox depositBox;

    @Column(name="payment_method_id", nullable=false)
    private Integer paymentMethodId;

    public Obsolete_ParamAllowedPaymentXDeposit() {
    }

    public Boolean isDefaultDeposit() {
        return defaultDeposit;
    }

    public void setDefaultDeposit(final Boolean defaultDepositFlg) {
        defaultDeposit = defaultDepositFlg;
    }

    public DepositBox getDepositBox() {
        return depositBox;
    }

    public void setDepositBox(final DepositBox depositBox) {
        depositBoxId = depositBoxId;
    }

    public Boolean getModificationStatusCd() {
        return modificationStatusCd;
    }

    public void setModificationStatusCd(final Boolean modificationStatusCd) {
        this.modificationStatusCd = modificationStatusCd;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(final Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Date getValidFromDttm() {
        return validFromDttm;
    }

    public void setValidFromDttm(final Date validFromDttm) {
        this.validFromDttm = validFromDttm;
    }

    public Date getValidToDttm() {
        return validToDttm;
    }

    public void setValidToDttm(final Date validToDttm) {
        this.validToDttm = validToDttm;
    }

    @Override
    public TemporalValidity getValidity() {
        return validity;
    }

}