package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.type.IncomeType;


/**
 * The persistent class for the employee_shift_income database table.
 *
 */
@Entity
@Table(name="employee_shift_income")
@NamedQuery(name="EmployeeShiftIncome.findAll", query="SELECT e FROM EmployeeShiftIncome e")
public class EmployeeShiftIncome implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EMPLOYEE_SHIFT_INCOME_EMPLOYEESHIFTINCOMEID_GENERATOR", sequenceName="EMPLOYEE_SHIFT_INCOME_EMPLOYEE_SHIFT_INCOME_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SHIFT_INCOME_EMPLOYEESHIFTINCOMEID_GENERATOR")
    @Column(name = "employee_shift_income_id", updatable = false)
    private Long id;

    @Column(name="income_amt", nullable=false, precision=12, scale=2)
    private BigDecimal incomeAmt;

    //bi-directional many-to-one association to EmployeeShift
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "employee_shift_id", nullable = false, updatable = false)
    private EmployeeShift employeeShift;

    //uni-directional many-to-one association to IncomeFormType
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="income_form_id", nullable=false)
    private IncomeType incomeForm;

    public EmployeeShiftIncome() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getIncomeAmt() {
        return incomeAmt;
    }

    public void setIncomeAmt(final BigDecimal incomeAmt) {
        this.incomeAmt = incomeAmt;
    }

    public EmployeeShift getEmployeeShift() {
        return employeeShift;
    }

    public void setEmployeeShift(final EmployeeShift employeeShift) {
        this.employeeShift = employeeShift;
    }

    public IncomeType getIncomeFormType() {
        return incomeForm;
    }

    public void setIncomeFormType(final IncomeType incomeFormType) {
        incomeForm = incomeFormType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmployeeShiftIncome [id=" + id + ", incomeAmt=" + incomeAmt + ", employeeShift=" + employeeShift
                + ", incomeForm=" + incomeForm + "]";
    }

}