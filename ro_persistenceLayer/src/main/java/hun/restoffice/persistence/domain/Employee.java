package hun.restoffice.persistence.domain;

import java.io.Serializable;
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
    @SequenceGenerator(name = "EMPLOYEE_EMPLOYEE_ID_GENERATOR", sequenceName = "EMPLOYEE_EMPLOYEE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_EMPLOYEE_ID_GENERATOR")
    @Column(name = "employee_id", updatable = false)
    private Long id;

    @Column(name = "employee_horuly_wage_amt", precision = 12, scale = 2)
    private double hourlyWage;

    @Column(name = "employee_tip_share_pct", precision = 2, scale = 2)
    private double tipSharePercentage;

    @Column(nullable = false, length = 50)
    private String name;

    // uni-directional many-to-one association to JobType
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_category_id", nullable = false)
    private JobCategory jobType;

    // bi-directional many-to-one association to EmployeeShift
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmployeeShift> employeeShifts = new ArrayList<>();

    @Embedded
    Effectivity effectivity;

    public Employee() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the hourlyWage
     */
    public double getHourlyWage() {
        return hourlyWage;
    }

    /**
     * @param hourlyWage
     *            the hourlyWage to set
     */
    public void setHourlyWage(final double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    /**
     * @return the tipSharePercentage
     */
    public double getTipSharePercentage() {
        return tipSharePercentage;
    }

    /**
     * @param tipSharePercentage
     *            the tipSharePercentage to set
     */
    public void setTipSharePercentage(final double tipSharePercentage) {
        this.tipSharePercentage = tipSharePercentage;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the jobType
     */
    public JobCategory getJobType() {
        return jobType;
    }

    /**
     * @param jobType
     *            the jobType to set
     */
    public void setJobType(final JobCategory jobType) {
        this.jobType = jobType;
    }

    /**
     * @return the employeeShifts
     */
    public List<EmployeeShift> getEmployeeShifts() {
        return employeeShifts;
    }

    /**
     * @param employeeShifts
     *            the employeeShifts to set
     */
    protected void setEmployeeShifts(final List<EmployeeShift> employeeShifts) {
        this.employeeShifts = employeeShifts;
    }

    /**
     * @return the effectivity
     */
    public Effectivity getEffectivity() {
        return effectivity;
    }

    /**
     * @param effectivity
     *            the effectivity to set
     */
    protected void setEffectivity(final Effectivity effectivity) {
        this.effectivity = effectivity;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Employee [id=" + id + ", hourlyWage=" + hourlyWage + ", tipSharePercentage=" + tipSharePercentage
                + ", name=" + name + ", jobType=" + jobType + ", employeeShifts=" + employeeShifts + ", effectivity="
                + effectivity + ", parameterType=" + super.toString() + "]";
    }
}