package hun.restoffice.persistence.type;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.util.ParameterType;


/**
 * The persistent class for the register_type database table.
 *
 */
@Entity
@Table(name="register_type")
@NamedQuery(name="RegisterType.findAll", query="SELECT r FROM RegisterType r")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "REGISTER_TYPE_REGISTER_TYPE_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "REGISTER_TYPE_ID", updatable = false))
public class RegisterType extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    //TODO: add mapping
    @Enumerated(EnumType.ORDINAL)
    private RegisterTypes registerType;

    // TODO: add mapping and join
    private List<IncomeType> meansOfIncome;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RegisterType [registerType=" + registerType + ", meansOfIncome=" + meansOfIncome + ", getId()="
                + this.getId() + ", getDescription()=" + this.getDescription() + ", getName()=" + this.getName()
                + ", getValidity()=" + this.getValidity() + "]";
    }

}