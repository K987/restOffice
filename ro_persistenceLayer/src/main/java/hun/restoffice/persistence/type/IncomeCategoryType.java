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
 * The persistent class for the income_type database table.
 *
 */
@Entity
@Table(name="income_type")
@NamedQuery(name="IncomeType.findAll", query="SELECT i FROM IncomeType i")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "INCOME_TYPE_INCOME_TYPE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "income_type_id", updatable = false))
public class IncomeCategoryType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    public IncomeCategoryType() {
    }
}