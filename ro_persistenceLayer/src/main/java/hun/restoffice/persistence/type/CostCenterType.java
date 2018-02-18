package hun.restoffice.persistence.type;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.util.ParameterType;

/**
 * The persistent class for the cost_center database table.
 *
 */
@Entity
@Table(name = "cost_center")
@NamedQuery(name = "CostCenter.findAll", query = "SELECT c FROM CostCenter c")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "COST_CENTER_COST_CENTER_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "COST_CENTER_ID", updatable = false))
public class CostCenterType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "parent_cost_center_id")
    @OneToOne(fetch = FetchType.EAGER)
    private CostCenterType parent;

    public CostCenterType getParent() {
        return parent;
    }

    public void setParent(final CostCenterType parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CostCenterType [parent=" + parent + ", getId()=" + this.getId() + ", getDescription()="
                + this.getDescription() + ", getName()=" + this.getName() + ", getValidity()=" + this.getValidity()
                + "]";
    }

}