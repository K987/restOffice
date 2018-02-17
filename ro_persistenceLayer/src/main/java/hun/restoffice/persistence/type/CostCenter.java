package hun.restoffice.persistence.type;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cost_center database table.
 *
 */
@Entity
@Table(name="cost_center")
@NamedQuery(name="CostCenter.findAll", query="SELECT c FROM CostCenter c")
public class CostCenter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="COST_CENTER_COSTCENTERID_GENERATOR", sequenceName="COST_CENTER_COST_CENTER_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COST_CENTER_COSTCENTERID_GENERATOR")
    @Column(name="cost_center_id", insertable=false, updatable=false, unique=true, nullable=false)
    private Long id;

    @Column(name="description_txt", length=500)
    private String description;

    @Column(name="modificaiton_status_cd", nullable=false)
    private Boolean valid;

    @Column(nullable=false, length=50)
    private String name;

    @JoinColumn(name = "parent_cost_center_id")
    @OneToOne(fetch = FetchType.EAGER)
    private CostCenter parent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="valid_from_dttm", nullable=false)
    private Date validFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="valid_to_dttm", nullable=false)
    private Date validTo;

    public CostCenter() {
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

    public Boolean getModificaitonStatus() {
        return valid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public CostCenter getParent() {
        return parent;
    }

    public void setParent(final CostCenter parent) {
        this.parent = parent;
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