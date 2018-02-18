package hun.restoffice.persistence.type;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.util.ParameterType;


/**
 * The persistent class for the cost_type database table.
 *
 */
@Entity
@Table(name="cost_type")
@NamedQuery(name="CostType.findAll", query="SELECT c FROM CostType c")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "COST_TYPE_COST_TYPE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "COST_TYPE_ID", updatable = false))
public class CostCategoryType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    public CostCategoryType() {

    }
}