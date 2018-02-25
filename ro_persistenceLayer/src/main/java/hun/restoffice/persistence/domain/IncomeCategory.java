package hun.restoffice.persistence.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the income_type database table.
 *
 */
@Entity
@Table(name = "INCOME_CATEGORY")
@NamedQuery(name = "IncomeCategory.findAll", query = "SELECT i FROM IncomeCategory i")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "INCOME_CATEGORY_INCOME_CATEGORY_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "INCOME_CATEGORY_id", updatable = false))
public class IncomeCategory extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    public IncomeCategory() {
    }
}