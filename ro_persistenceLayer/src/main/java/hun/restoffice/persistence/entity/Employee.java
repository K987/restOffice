package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.type.JobType;
import hun.restoffice.persistence.util.Effectivity;

/**
 * The persistent class for the employee database table.
 *
 */
@Entity
@Table(name = "employee")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EMPLOYEE_EMPLOYEEID_GENERATOR", sequenceName = "EMPLOYEE_EMPLOYEE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_EMPLOYEEID_GENERATOR")
    @Column(name = "employee_id", updatable = false)
    private Long id;

    @Column(name = "employee_horuly_wage_amt", precision = 12, scale = 2)
    private BigDecimal hourlyWage;

    @Column(name = "employee_tip_share_pct", precision = 2, scale = 2)
    private BigDecimal tipSharePct;

    @Column(nullable = false, length = 50)
    private String name;

    // uni-directional many-to-one association to JobType
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_type_id", nullable = false)
    private JobType jobType;

    // bi-directional many-to-one association to EmployeeShift
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeShift> employeeShifts = new ArrayList<>();

    @Embedded
    Effectivity effectivity;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(final BigDecimal employeeHorulyWageAmt) {
        hourlyWage = employeeHorulyWageAmt;
    }

    public BigDecimal getTipSharePct() {
        return tipSharePct;
    }

    public void setTipSharePct(final BigDecimal employeeTipSharePct) {
        tipSharePct = employeeTipSharePct;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Effectivity getEffectivity() {
        return effectivity;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(final JobType jobType) {
        this.jobType = jobType;
    }

    public List<EmployeeShift> getEmployeeShifts() {
        return employeeShifts;
    }

    // public void setEmployeeShifts(final List<EmployeeShift> employeeShifts) {
    // this.employeeShifts = employeeShifts;
    // }

    public EmployeeShift addEmployeeShift(final EmployeeShift employeeShift) {
        getEmployeeShifts().add(employeeShift);
        employeeShift.setEmployee(this);

        return employeeShift;
    }

    public EmployeeShift removeEmployeeShift(final EmployeeShift employeeShift) {
        getEmployeeShifts().remove(employeeShift);
        employeeShift.setEmployee(null);

        return employeeShift;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Employee [id=" + id + ", hourlyWage=" + hourlyWage + ", tipSharePct=" + tipSharePct + ", name=" + name
                + ", jobType=" + jobType + ", employeeShifts=" + employeeShifts + ", effectivity=" + effectivity + "]";
    }

}