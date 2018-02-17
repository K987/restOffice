package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the param_income_form_x_deposit_box database table.
 * 
 */
@Entity
@Table(name="param_income_form_x_deposit_box")
@NamedQuery(name="ParamIncomeFormXDepositBox.findAll", query="SELECT p FROM ParamIncomeFormXDepositBox p")
public class ParamIncomeFormXDepositBox implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PARAM_INCOME_FORM_X_DEPOSIT_BOX_INCOMEFORMXDEPOSITBOXID_GENERATOR", sequenceName="PARAM_INCOME_FORM_X_DEPOSIT_BOX_INCOME_FORM_X_DEPOSIT_BOX_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARAM_INCOME_FORM_X_DEPOSIT_BOX_INCOMEFORMXDEPOSITBOXID_GENERATOR")
	@Column(name="income_form_x_deposit_box_id", unique=true, nullable=false)
	private Integer incomeFormXDepositBoxId;

	@Column(name="deposit_box_id", nullable=false)
	private Integer depositBoxId;

	@Column(name="income_form_id", nullable=false)
	private Integer incomeFormId;

	@Column(name="modification_status_cd", nullable=false)
	private Boolean modificationStatusCd;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_from_dttm", nullable=false)
	private Date validFromDttm;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_to_dttm", nullable=false)
	private Date validToDttm;

	public ParamIncomeFormXDepositBox() {
	}

	public Integer getIncomeFormXDepositBoxId() {
		return this.incomeFormXDepositBoxId;
	}

	public void setIncomeFormXDepositBoxId(Integer incomeFormXDepositBoxId) {
		this.incomeFormXDepositBoxId = incomeFormXDepositBoxId;
	}

	public Integer getDepositBoxId() {
		return this.depositBoxId;
	}

	public void setDepositBoxId(Integer depositBoxId) {
		this.depositBoxId = depositBoxId;
	}

	public Integer getIncomeFormId() {
		return this.incomeFormId;
	}

	public void setIncomeFormId(Integer incomeFormId) {
		this.incomeFormId = incomeFormId;
	}

	public Boolean getModificationStatusCd() {
		return this.modificationStatusCd;
	}

	public void setModificationStatusCd(Boolean modificationStatusCd) {
		this.modificationStatusCd = modificationStatusCd;
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