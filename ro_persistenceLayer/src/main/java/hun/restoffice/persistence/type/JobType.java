package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the job_type database table.
 * 
 */
@Entity
@Table(name="job_type")
@NamedQuery(name="JobType.findAll", query="SELECT j FROM JobType j")
public class JobType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="JOB_TYPE_JOBTYPEID_GENERATOR", sequenceName="JOB_TYPE_JOB_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="JOB_TYPE_JOBTYPEID_GENERATOR")
	@Column(name="job_type_id", unique=true, nullable=false)
	private Integer jobTypeId;

	@Column(name="description_txt", length=500)
	private String descriptionTxt;

	@Column(name="job_hourly_wage_amt", nullable=false, precision=12, scale=2)
	private BigDecimal jobHourlyWageAmt;

	@Column(name="job_tip_share_pct", nullable=false, precision=2, scale=2)
	private BigDecimal jobTipSharePct;

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

	public JobType() {
	}

	public Integer getJobTypeId() {
		return this.jobTypeId;
	}

	public void setJobTypeId(Integer jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public String getDescriptionTxt() {
		return this.descriptionTxt;
	}

	public void setDescriptionTxt(String descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	public BigDecimal getJobHourlyWageAmt() {
		return this.jobHourlyWageAmt;
	}

	public void setJobHourlyWageAmt(BigDecimal jobHourlyWageAmt) {
		this.jobHourlyWageAmt = jobHourlyWageAmt;
	}

	public BigDecimal getJobTipSharePct() {
		return this.jobTipSharePct;
	}

	public void setJobTipSharePct(BigDecimal jobTipSharePct) {
		this.jobTipSharePct = jobTipSharePct;
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