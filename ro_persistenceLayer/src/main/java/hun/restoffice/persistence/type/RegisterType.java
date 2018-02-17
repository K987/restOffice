package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the register_type database table.
 * 
 */
@Entity
@Table(name="register_type")
@NamedQuery(name="RegisterType.findAll", query="SELECT r FROM RegisterType r")
public class RegisterType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REGISTER_TYPE_REGISTERTYPEID_GENERATOR", sequenceName="REGISTER_TYPE_REGISTER_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTER_TYPE_REGISTERTYPEID_GENERATOR")
	@Column(name="register_type_id", updatable=false, unique=true, nullable=false)
	private Long registerTypeId;

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

	public RegisterType() {
	}

	public Long getRegisterTypeId() {
		return this.registerTypeId;
	}

	private void setRegisterTypeId(Long registerTypeId) {
		this.registerTypeId = registerTypeId;
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