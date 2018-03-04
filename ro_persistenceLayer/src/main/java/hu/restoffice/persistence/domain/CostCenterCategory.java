package hu.restoffice.persistence.domain;

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

/**
 * The persistent class for the cost_center database table.
 *
 */
@Entity
@Table(name = "COST_CENTER_CATEGORY")
@NamedQuery(name = "CostCenterCategory.findAll", query = "SELECT c FROM CostCenterCategory c")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "COST_CENTER_CATEGORY_COST_CENTER_CATEGORY_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "COST_CENTER_CATEGORY_ID", updatable = false))
public class CostCenterCategory extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "parent_cost_center_id")
    @OneToOne(fetch = FetchType.EAGER)
    private CostCenterCategory parent;

    public CostCenterCategory getParent() {
        return parent;
    }

    public void setParent(final CostCenterCategory parent) {
        this.parent = parent;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CostCenterType [parent=" + parent + ", parameterType=" + super.toString() + "]";
    }

}