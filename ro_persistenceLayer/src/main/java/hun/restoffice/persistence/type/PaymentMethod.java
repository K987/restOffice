package hun.restoffice.persistence.type;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the payment_method database table.
 * 
 */
@Entity
@Table(name="payment_method")
@NamedQuery(name="PaymentMethod.findAll", query="SELECT p FROM PaymentMethod p")
public class PaymentMethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_METHOD_PAYMENTMETHODID_GENERATOR", sequenceName="PAYMENT_METHOD_PAYMENT_METHOD_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_METHOD_PAYMENTMETHODID_GENERATOR")
	@Column(name="payment_method_id", unique=true, nullable=false)
	private Integer paymentMethodId;

	@Column(name="description_txt", length=500)
	private String descriptionTxt;

	@Column(name="modification_status_cd", nullable=false)
	private Boolean modificationStatusCd;

	@Column(nullable=false, length=50)
	private String name;

	@Column(name="payment_method_type_cd", nullable=false)
	private Integer paymentMethodTypeCd;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_from_dttm", nullable=false)
	private Date validFromDttm;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_to_dttm", nullable=false)
	private Date validToDttm;

	public PaymentMethod() {
	}

	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}

	public String getDescriptionTxt() {
		return descriptionTxt;
	}

	public void setDescriptionTxt(final String descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	public Boolean getModificationStatusCd() {
		return modificationStatusCd;
	}

	public void setModificationStatusCd(final Boolean modificationStatusCd) {
		this.modificationStatusCd = modificationStatusCd;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getPaymentMethodTypeCd() {
		return paymentMethodTypeCd;
	}

	public void setPaymentMethodTypeCd(final Integer paymentMethodTypeCd) {
		this.paymentMethodTypeCd = paymentMethodTypeCd;
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

}