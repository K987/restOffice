package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Transient;

import hun.restoffice.persistence.type.JobType;

/**
 * The persistent class for the employee_shift database table.
 *
 */
@Entity
@Table(name = "employee_shift")
@NamedQuery(name = "EmployeeShift.findAll", query = "SELECT e FROM EmployeeShift e")
public class EmployeeShift implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "EMPLOYEE_SHIFT_EMPLOYEESHIFTID_GENERATOR", sequenceName = "EMPLOYEE_SHIFT_EMPLOYEE_SHIFT_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SHIFT_EMPLOYEESHIFTID_GENERATOR")
    @Column(name = "employee_shift_id", updatable = false)
    private Long id;

    @Column(name = "actual_hourly_wage", precision = 12, scale = 2)
    private BigDecimal hourlyWage;

    @Column(name = "actual_tip_share_pct", precision = 2, scale = 2)
    private BigDecimal tipSharePct;

    @Column(name = "create_dttm", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "end_dttm", nullable = false)
    private LocalDateTime shiftEnd;

    @Column(name = "pos_income_amt", nullable = false, precision = 12, scale = 2)
    private BigDecimal posIncome;

    @Column(name = "start_dttm", nullable = false)
    private LocalDateTime shifStart;

    // bi-directional many-to-one association to DailyClose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_close_id")
    private DailyClose dailyClose;

    // bi-directional many-to-one association to Employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // uni-directional many-to-one association to JobType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actual_job_type_id")
    private JobType jobType;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id", nullable = false)
    private User createUser;

    // bi-directional many-to-one association to EmployeeShiftIncome
    @OneToMany(mappedBy = "employeeShift", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EmployeeShiftIncome> incomes = new ArrayList<>();

    @Transient
    private Duration hoursWorked;

    public EmployeeShift() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(final BigDecimal actualHourlyWage) {
        hourlyWage = actualHourlyWage;
    }

    public BigDecimal getTipSharePct() {
        return tipSharePct;
    }

    public void setTipSharePct(final BigDecimal actualTipSharePct) {
        tipSharePct = actualTipSharePct;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createDttm) {
        createdAt = createDttm;
    }

    public LocalDateTime getShiftEnd() {
        return shiftEnd;
    }

    public void setEndDttm(final LocalDateTime shiftEndDttm) {
        shiftEnd = shiftEndDttm;
        updateHoursWorked();
    }

    public BigDecimal getPosIncomeAmt() {
        return posIncome;
    }

    public void setPosIncome(final BigDecimal posIncomeAmt) {
        posIncome = posIncomeAmt;
    }

    public LocalDateTime getShifStart() {
        return shifStart;
    }

    public void setShifStart(final LocalDateTime startDttm) {
        shifStart = startDttm;
        updateHoursWorked();
    }

    public DailyClose getDailyClose() {
        return dailyClose;
    }

    public void setDailyClose(final DailyClose dailyClose) {
        this.dailyClose = dailyClose;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(final Employee employee) {
        this.employee = employee;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(final JobType jobType) {
        this.jobType = jobType;
    }

    public User getRoUser() {
        return createUser;
    }

    public void setRoUser(final User roUser) {
        createUser = roUser;
    }

    public List<EmployeeShiftIncome> getIncomes() {
        return incomes;
    }

    // public void setEmployeeShiftIncomes(final List<EmployeeShiftIncome>
    // employeeShiftIncomes) {
    // this.employeeShiftIncomes = employeeShiftIncomes;
    // }

    public EmployeeShiftIncome addEmployeeShiftIncome(final EmployeeShiftIncome employeeShiftIncome) {
        getIncomes().add(employeeShiftIncome);
        employeeShiftIncome.setEmployeeShift(this);

        return employeeShiftIncome;
    }

    public EmployeeShiftIncome removeEmployeeShiftIncome(final EmployeeShiftIncome employeeShiftIncome) {
        getIncomes().remove(employeeShiftIncome);
        employeeShiftIncome.setEmployeeShift(null);

        return employeeShiftIncome;
    }

    public Duration getHoursWorked() {
        if (hoursWorked == null)
            updateHoursWorked();
        return hoursWorked;

    }

    /**
     *
     */
    private void updateHoursWorked() {
        if (shifStart != null && shiftEnd != null)
            hoursWorked = Duration.between(shifStart, shiftEnd);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmployeeShift [id=" + id + ", hourlyWage=" + hourlyWage + ", tipSharePct=" + tipSharePct
                + ", createdAt=" + createdAt + ", shiftEnd=" + shiftEnd + ", posIncome=" + posIncome + ", shifStart="
                + shifStart + ", dailyClose=" + dailyClose + ", employee=" + employee + ", jobType=" + jobType
                + ", createUser=" + createUser + ", incomes=" + incomes + ", hoursWorked=" + hoursWorked + "]";
    }
}