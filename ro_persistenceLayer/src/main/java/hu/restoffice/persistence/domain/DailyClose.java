package hu.restoffice.persistence.domain;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the daily_close database table.
 *
 */
@Entity
@Table(name = "daily_close")
@NamedQuery(name = "DailyClose.findAll", query = "SELECT d FROM DailyClose d")
public class DailyClose implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "DAILY_CLOSE_DAILY_CLOSE_ID_GENERATOR", sequenceName = "DAILY_CLOSE_DAILY_CLOSE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DAILY_CLOSE_DAILY_CLOSE_ID_GENERATOR")
    @Column(name = "daily_close_id", updatable = false)
    private Long id;

    @Column(name = "registered_total_amt", nullable = false, precision = 12, scale = 2)
    private double totalRegistered;

    @Column(name = "income_total_amt", nullable = false, precision = 12, scale = 2)
    private double totalIncome;

    @Column(name = "pos_total_amt", nullable = false, precision = 12, scale = 2)
    private double totalPos;

    @Column(name = "close_day_dt", nullable = false)
    private LocalDate closeDay;

    @Column(name = "booked_dttm", updatable = false)
    private LocalDateTime booked;

    // bi-directional many-to-one association to DailyCloseDetail
    @OneToMany(mappedBy = "dailyClose", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<DailyCloseDetail> dailyCloseDetails = new ArrayList<>();

    // bi-directional many-to-one association to EmployeeShift
    @OneToMany(mappedBy = "dailyClose", fetch = FetchType.LAZY)
    private List<EmployeeShift> employeeShifts = new ArrayList<>();;

    // bi-directional many-to-one association to RegisterClose
    @OneToMany(mappedBy = "dailyClose", fetch = FetchType.LAZY)
    private List<RegisterClose> registerCloses = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "daily_close_x_income", joinColumns = @JoinColumn(name = "daily_close_id", referencedColumnName = "daily_close_id"), inverseJoinColumns = @JoinColumn(name = "income_id", referencedColumnName = "income_id"))
    private List<Income> income;

    @Embedded
    private UserAction action;

    public DailyClose() {
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
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the totalRegistered
     */
    public double getTotalRegistered() {
        return totalRegistered;
    }

    /**
     * @param totalRegistered
     *            the totalRegistered to set
     */
    public void setTotalRegistered(final double totalRegistered) {
        this.totalRegistered = totalRegistered;
    }

    /**
     * @return the totalIncome
     */
    public double getTotalIncome() {
        return totalIncome;
    }

    /**
     * @param totalIncome
     *            the totalIncome to set
     */
    public void setTotalIncome(final double totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     * @return the totalPos
     */
    public double getTotalPos() {
        return totalPos;
    }

    /**
     * @param totalPos
     *            the totalPos to set
     */
    public void setTotalPos(final double totalPos) {
        this.totalPos = totalPos;
    }

    /**
     * @return the closeDay
     */
    public LocalDate getCloseDay() {
        return closeDay;
    }

    /**
     * @param closeDay
     *            the closeDay to set
     */
    public void setCloseDay(final LocalDate closeDay) {
        this.closeDay = closeDay;
    }

    /**
     * @return the booked
     */
    public LocalDateTime getBooked() {
        return booked;
    }

    /**
     * @param booked
     *            the booked to set
     */
    protected void setBooked(final LocalDateTime booked) {
        this.booked = booked;
    }

    /**
     * @return the dailyCloseDetails
     */
    public List<DailyCloseDetail> getDailyCloseDetails() {
        return dailyCloseDetails;
    }

    /**
     * @param dailyCloseDetails
     *            the dailyCloseDetails to set
     */
    protected void setDailyCloseDetails(final List<DailyCloseDetail> dailyCloseDetails) {
        this.dailyCloseDetails = dailyCloseDetails;
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
     * @return the registerCloses
     */
    public List<RegisterClose> getRegisterCloses() {
        return registerCloses;
    }

    /**
     * @param registerCloses
     *            the registerCloses to set
     */
    protected void setRegisterCloses(final List<RegisterClose> registerCloses) {
        this.registerCloses = registerCloses;
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
    public void setAction(final UserAction action) {
        this.action = action;
    }

    public DailyCloseDetail addDailyCloseDetail(final DailyCloseDetail dailyCloseDetail) {
        getDailyCloseDetails().add(dailyCloseDetail);
        dailyCloseDetail.setDailyClose(this);

        return dailyCloseDetail;
    }

    public DailyCloseDetail removeDailyCloseDetail(final DailyCloseDetail dailyCloseDetail) {
        getDailyCloseDetails().remove(dailyCloseDetail);
        dailyCloseDetail.setDailyClose(null);

        return dailyCloseDetail;
    }

    public EmployeeShift addEmployeeShift(final EmployeeShift employeeShift) {
        getEmployeeShifts().add(employeeShift);
        employeeShift.setDailyClose(this);

        return employeeShift;
    }

    public EmployeeShift removeEmployeeShift(final EmployeeShift employeeShift) {
        getEmployeeShifts().remove(employeeShift);
        employeeShift.setDailyClose(null);

        return employeeShift;
    }

    public RegisterClose addRegisterClose(final RegisterClose registerClose) {
        getRegisterCloses().add(registerClose);
        registerClose.setDailyClose(this);

        return registerClose;
    }

    public RegisterClose removeRegisterClose(final RegisterClose registerClose) {
        getRegisterCloses().remove(registerClose);
        registerClose.setDailyClose(null);

        return registerClose;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DailyClose [id=" + id + ", totalRegistered=" + totalRegistered + ", totalIncome=" + totalIncome
                + ", totalPos=" + totalPos + ", closeDay=" + closeDay + ", booked=" + booked + ", dailyCloseDetails="
                + dailyCloseDetails + ", employeeShifts=" + employeeShifts + ", registerCloses=" + registerCloses
                + ", action=" + action + "]";
    }
}