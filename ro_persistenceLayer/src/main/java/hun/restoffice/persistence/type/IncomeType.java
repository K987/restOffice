package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the income_type database table.
 * 
 */
@Entity
@Table(name="income_type")
@NamedQuery(name="IncomeType.findAll", query="SELECT i FROM IncomeType i")
public class IncomeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INCOME_TYPE_INCOMETYPEID_GENERATOR", sequenceName="INCOME_TYPE_INCOME_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCOME_TYPE_INCOMETYPEID_GENERATOR")
	@Column(name="income_type_id", unique=true, nullable=false)
	private Integer incomeTypeId;

	@Column(name="description_txt", length=500)
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

	public IncomeType() {
	}

	public Integer getIncomeTypeId() {
		return this.incomeTypeId;
	}

	public void setIncomeTypeId(Integer incomeTypeId) {
		this.incomeTypeId = incomeTypeId;
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