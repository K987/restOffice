package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    @SequenceGenerator(name = "EMPLOYEE_SHIFT_EMPLOYEE_SHIFT_ID_GENERATOR", sequenceName = "EMPLOYEE_SHIFT_EMPLOYEE_SHIFT_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_SHIFT_EMPLOYEE_SHIFT_ID_GENERATOR")
    @Column(name = "employee_shift_id", updatable = false)
    private Long id;

    @Column(name = "actual_hourly_wage_amt", precision = 12, scale = 2)
    private double hourlyWage;

    @Column(name = "actual_tip_share_pct", precision = 2, scale = 2)
    private double tipSharePct;

    @Column(name = "start_dttm", nullable = false)
    private LocalDateTime shiftStart;

    @Column(name = "end_dttm", nullable = false)
    private LocalDateTime shiftEnd;

    @Column(name = "pos_income_amt", nullable = false, precision = 12, scale = 2)
    private double posIncome;

    // bi-directional many-to-one association to EmployeeShiftIncome
    @OneToMany(mappedBy = "employeeShift", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EmployeeShiftIncome> incomes = new ArrayList<>();

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
    @JoinColumn(name = "actual_job_category_id")
    private JobCategory jobType;

    @Embedded
    private UserAction action;

    @Transient
    private Duration hoursWorked;

    public EmployeeShift() {
    }

    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(final double actualHourlyWage) {
        hourlyWage = actualHourlyWage;
    }

    public double getTipSharePct() {
        return tipSharePct;
    }

    public void setTipSharePct(final double actualTipSharePct) {
        tipSharePct = actualTipSharePct;
    }

    /**
     * @return the shiftStart
     */
    public LocalDateTime getShiftStart() {
        return shiftStart;
    }

    /**
     * @param shiftStart
     *            the shiftStart to set
     */
    public void setShiftStart(final LocalDateTime shiftStart) {
        this.shiftStart = shiftStart;
        updateHoursWorked();
    }

    public LocalDateTime getShiftEnd() {
        return shiftEnd;
    }

    /**
     * @param shiftEnd
     *            the shiftEnd to set
     */
    public void setShiftEnd(final LocalDateTime shiftEnd) {
        this.shiftEnd = shiftEnd;
        updateHoursWorked();
    }

    /**
     * @return the posIncome
     */
    public double getPosIncome() {
        return posIncome;
    }

    public double getPosIncomeAmt() {
        return posIncome;
    }

    public void setPosIncome(final double posIncomeAmt) {
        posIncome = posIncomeAmt;
    }

    public List<EmployeeShiftIncome> getIncomes() {
        return incomes;
    }

    /**
     * @param incomes
     *            the incomes to set
     */
    protected void setIncomes(final List<EmployeeShiftIncome> incomes) {
        this.incomes = incomes;
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

    public JobCategory getJobType() {
        return jobType;
    }

    public void setJobType(final JobCategory jobType) {
        this.jobType = jobType;
    }

    protected void setEmployeeShiftIncomes(final List<EmployeeShiftIncome> employeeShiftIncomes) {
        incomes = employeeShiftIncomes;
    }

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

        return hoursWorked;
    }

    /**
     * @return the action
     */
    public UserAction getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    protected void setAction(final UserAction action) {
        this.action = action;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmployeeShift [id=" + id + ", hourlyWage=" + hourlyWage + ", tipSharePct=" + tipSharePct
                + ", shiftStart=" + shiftStart + ", shiftEnd=" + shiftEnd + ", posIncome=" + posIncome + ", incomes="
                + incomes + ", dailyClose=" + dailyClose + ", employee=" + employee + ", jobType=" + jobType
                + ", action=" + action + ", hoursWorked=" + hoursWorked + "]";
    }

    /**
     *
     */
    @PostLoad
    protected void updateHoursWorked() {
        if (shiftStart != null && shiftEnd != null)
            hoursWorked = Duration.between(shiftStart, shiftEnd);
    }


}