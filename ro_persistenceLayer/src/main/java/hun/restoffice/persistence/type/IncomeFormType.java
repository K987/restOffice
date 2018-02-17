package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the income_form_type database table.
 * 
 */
@Entity
@Table(name="income_form_type")
@NamedQuery(name="IncomeFormType.findAll", query="SELECT i FROM IncomeFormType i")
public class IncomeFormType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INCOME_FORM_TYPE_INCOMEFORMTYPEID_GENERATOR", sequenceName="INCOME_FORM_TYPE_INCOME_FORM_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCOME_FORM_TYPE_INCOMEFORMTYPEID_GENERATOR")
	@Column(name="income_form_type_id", unique=true, nullable=false)
	private Integer incomeFormTypeId;

	@Column(name="description_txt", nullable=false, length=500)
	private String descriptionTxt;

	@Column(name="modification_status_cd", nullable=false)
	private Boolean modificationStatusCd;

	@Column(nullable=false, length=50)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_from_dttm", nullable=false)
	private Date validFromDttm;

	@Temporal(TemporalType.DATE)
	@Column(name="valid_to_dttm", nullable=false)
	private Date validToDttm;

	public IncomeFormType() {
	}

	public Integer getIncomeFormTypeId() {
		return this.incomeFormTypeId;
	}

	public void setIncomeFormTypeId(Integer incomeFormTypeId) {
		this.incomeFormTypeId = incomeFormTypeId;
	}

	public String getDescriptionTxt() {
		return this.descriptionTxt;
	}

	public void setDescriptionTxt(String descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	public Boolean getModificationStatusCd() {
		return this.modificationStatusCd;
	}

	public void setModificationStatusCd(Boolean modificationStatusCd) {
		this.modificationStatusCd = modificationStatusCd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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