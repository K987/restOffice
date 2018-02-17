package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the param_allowed_payment_x_deposit database table.
 * 
 */
@Entity
@Table(name="param_allowed_payment_x_deposit")
@NamedQuery(name="ParamAllowedPaymentXDeposit.findAll", query="SELECT p FROM ParamAllowedPaymentXDeposit p")
public class ParamAllowedPaymentXDeposit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PARAM_ALLOWED_PAYMENT_X_DEPOSIT_ALLOWEDPAYMENTXDEPOSITBOXID_GENERATOR", sequenceName="PARAM_ALLOWED_PAYMENT_X_DEPOSIT_ALLOWED_PAYMENT_X_DEPOSIT_BOX_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARAM_ALLOWED_PAYMENT_X_DEPOSIT_ALLOWEDPAYMENTXDEPOSITBOXID_GENERATOR")
	@Column(name="allowed_payment_x_deposit_box_id", unique=true, nullable=false)
	private Integer allowedPaymentXDepositBoxId;

	@Column(name="default_deposit_flg", nullable=false)
	private Boolean defaultDepositFlg;

	@Column(name="deposit_box_id", nullable=false)
	private Integer depositBoxId;

	@Column(name="modification_status_cd", nullable=false)
	private Boolean modificationStatusCd;

	@Column(name="payment_method_id", nullable=false)
	private Integer paymentMethodId;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_from_dttm", nullable=false)
	private Date validFromDttm;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_to_dttm", nullable=false)
	private Date validToDttm;

	public ParamAllowedPaymentXDeposit() {
	}

	public Integer getAllowedPaymentXDepositBoxId() {
		return this.allowedPaymentXDepositBoxId;
	}

	public void setAllowedPaymentXDepositBoxId(Integer allowedPaymentXDepositBoxId) {
		this.allowedPaymentXDepositBoxId = allowedPaymentXDepositBoxId;
	}

	public Boolean getDefaultDepositFlg() {
		return this.defaultDepositFlg;
	}

	public void setDefaultDepositFlg(Boolean defaultDepositFlg) {
		this.defaultDepositFlg = defaultDepositFlg;
	}

	public Integer getDepositBoxId() {
		return this.depositBoxId;
	}

	public void setDepositBoxId(Integer depositBoxId) {
		this.depositBoxId = depositBoxId;
	}

	public Boolean getModificationStatusCd() {
		return this.modificationStatusCd;
	}

	public void setModificationStatusCd(Boolean modificationStatusCd) {
		this.modificationStatusCd = modificationStatusCd;
	}

	public Integer getPaymentMethodId() {
		return this.paymentMethodId;
	}

	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Date getValidFromDttm() {
		return this.validFromDttm;
	}

	public void setValidFromDttm(Date validFromDttm) {
		this.validFromDttm = validFromDttm;
	}

	public Date getValidToDttm() {
		return this.validToDttm;
	}

	public void setValidToDttm(Date validToDttm) {
		this.validToDttm = validToDttm;
	}

}