package hun.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the cost_type database table.
 *
 */
@Entity
@Table(name = "COST_CATEGORY")
@NamedQuery(name = "CostCategory.findAll", query = "SELECT c FROM CostCategory c")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "COST_CATEGORY_COST_CATEGORY_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "COST_CATEGORY_ID", updatable = false))
public class CostCategory extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    public CostCategory() {

    }
}