package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the register_type database table.
 *
 */
@Entity
@Table(name = "REGISTER_CATEGORY")
@NamedQuery(name = "RegisterCategory.findAll", query = "SELECT r FROM RegisterCategory r")
@SequenceGenerator(name = "PARAMETER_TYPE_ID_GENERATOR", sequenceName = "REGISTER_CATEGORY_REGISTER_CATEGORY_ID_SEQ")
@AttributeOverride(name = "id", column = @Column(name = "REGISTER_TYPE_ID", updatable = false))
public class RegisterCategory extends ParameterType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "register_type_cd", nullable = false)
    private RegisterTypes registerType;

    @OneToMany(mappedBy = "registerType")
    private List<IncomeFormCategory> handledIncomes = new ArrayList<>();

    /**
     * @return the registerType
     */
    public RegisterTypes getRegisterType() {
        return registerType;
    }

    /**
     * @param registerType
     *            the registerType to set
     */
    public void setRegisterType(final RegisterTypes registerType) {
        this.registerType = registerType;
    }

    /**
     * @return the handledIncomes
     */
    public List<IncomeFormCategory> getHandledIncomes() {
        return handledIncomes;
    }

    /**
     * @param handledIncomes
     *            the handledIncomes to set
     */
    public void setHandledIncomes(final List<IncomeFormCategory> handledIncomes) {
        this.handledIncomes = handledIncomes;
    }

    public IncomeFormCategory addIncomeFormTyppe(final IncomeFormCategory incomeFormType) {
        handledIncomes.add(incomeFormType);
        incomeFormType.setRegisterType(this);
        return incomeFormType;
    }

    public IncomeFormCategory removeIncomeFormTyppe(final IncomeFormCategory incomeFormType) {
        handledIncomes.remove(incomeFormType);
        incomeFormType.setRegisterType(this);
        return incomeFormType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RegisterType [registerType=" + registerType + ", handledIncomes=" + handledIncomes + ", parameterType="
                + super.toString() + "]";
    }

}