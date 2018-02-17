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
 * The persistent class for the cost_type database table.
 *
 */
@Entity
@Table(name="cost_type")
@NamedQuery(name="CostType.findAll", query="SELECT c FROM CostType c")
public class CostType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="COST_TYPE_COSTTYPEID_GENERATOR", sequenceName="COST_TYPE_COST_TYPE_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COST_TYPE_COSTTYPEID_GENERATOR")
    @Column(name="cost_type_id", updatable=false, unique=true, nullable=false)
    private Long id;

    @Column(name="description_txt", length=500)
    private String description;

    @Column(name="modification_status_cd", nullable=false)
    private Boolean valid;

    @Column(nullable=false, length=50)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="valid_from_dttm", nullable=false)
    private Date validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="valid_to_dttm", nullable=false)
    private Date validTo;

    public CostType() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Boolean getModificationStatusCd() {
        return valid;
    }

    public void setModificationStatusCd(final Boolean modificationStatusCd) {
        valid = modificationStatusCd;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(final Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(final Date validTo) {
        this.validTo = validTo;
    }

}