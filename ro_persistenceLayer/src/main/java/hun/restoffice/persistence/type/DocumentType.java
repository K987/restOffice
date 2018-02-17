package hun.restoffice.persistence.type;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the document_type database table.
 * 
 */
@Entity
@Table(name="document_type")
@NamedQuery(name="DocumentType.findAll", query="SELECT d FROM DocumentType d")
public class DocumentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DOCUMENT_TYPE_DOCUMENTTYPEID_GENERATOR", sequenceName="DOCUMENT_TYPE_DOCUMENT_TYPE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCUMENT_TYPE_DOCUMENTTYPEID_GENERATOR")
	@Column(name="document_type_id", updatable=false, unique=true, nullable=false)
	private Long documentTypeId;

	@Column(name="description_txt", length=500)
	private String descriptionTxt;

	@Column(name="document_type_type_cd", nullable=false)
	private Integer documentTypeTypeCd;

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

	public DocumentType() {
	}

	public Long getDocumentTypeId() {
		return this.documentTypeId;
	}

	private void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDescriptionTxt() {
		return this.descriptionTxt;
	}

	public void setDescriptionTxt(String descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	public Integer getDocumentTypeTypeCd() {
		return this.documentTypeTypeCd;
	}

	public void setDocumentTypeTypeCd(Integer documentTypeTypeCd) {
		this.documentTypeTypeCd = documentTypeTypeCd;
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