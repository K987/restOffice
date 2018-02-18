package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
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
    @SequenceGenerator(name = "DAILY_CLOSE_DAILYCLOSEID_GENERATOR", sequenceName = "DAILY_CLOSE_DAILY_CLOSE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DAILY_CLOSE_DAILYCLOSEID_GENERATOR")
    @Column(name = "daily_close_id", updatable = false)
    private Long id;

    @Column(name = "registered_total_amt", nullable = false, precision = 12, scale = 2)
    private double registeredTotal;

    @Column(name = "income_total_amt", nullable = false, precision = 12, scale = 2)
    private double incomeTotal;

    @Column(name = "pos_total_amt", nullable = false, precision = 12, scale = 2)
    private double posTotal;

    @Column(name = "close_day_dt", nullable = false)
    private LocalDate closeDay;

    @Column(name = "booked_dttm")
    private LocalDateTime bookedAt;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "booked_user_id")
    private User bookedBy;

    @Column(name = "create_dttm", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // uni-directional many-to-one association to RoUser
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "create_user_id", updatable = false)
    private User createdBy;

    // bi-directional many-to-one association to DailyCloseDetail
    @OneToMany(mappedBy = "dailyClose", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<DailyCloseDetail> dailyCloseDetails = new ArrayList<>();

    // bi-directional many-to-one association to EmployeeShift
    @OneToMany(mappedBy = "dailyClose", fetch = FetchType.LAZY)
    private List<EmployeeShift> employeeShifts = new ArrayList<>();;

    // bi-directional many-to-one association to RegisterClose
    @OneToMany(mappedBy = "dailyClose", fetch = FetchType.LAZY)
    private List<RegisterClose> registerCloses = new ArrayList<>();

    public DailyClose() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedDttm(final LocalDateTime bookedDttm) {
        bookedAt = bookedDttm;
    }

    public double getRegisteredTotal() {
        return registeredTotal;
    }

    public void setRegisteredTotal(final double registeredTotal) {
        this.registeredTotal = registeredTotal;
    }

    public LocalDate getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(final LocalDate closeDayDt) {
        closeDay = closeDayDt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createDttm) {
        createdAt = createDttm;
    }

    public double getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(final double incomeTotalAmt) {
        incomeTotal = incomeTotalAmt;
    }

    public double getPosTotal() {
        return posTotal;
    }

    public void setPosTotal(final double posTotalAmt) {
        posTotal = posTotalAmt;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(final User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public User getCreateBy() {
        return createdBy;
    }

    public void setCreateBy(final User createBy) {
        createdBy = createBy;
    }

    public List<DailyCloseDetail> getDailyCloseDetails() {
        return dailyCloseDetails;
    }

    // public void setDailyCloseDetails(final List<DailyCloseDetail>
    // dailyCloseDetails) {
    // this.dailyCloseDetails = dailyCloseDetails;
    // }

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

    public List<EmployeeShift> getEmployeeShifts() {
        return employeeShifts;
    }

    // public void setEmployeeShifts(final List<EmployeeShift> employeeShifts) {
    // this.employeeShifts = employeeShifts;
    // }

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

    public List<RegisterClose> getRegisterCloses() {
        return registerCloses;
    }

    // public void setRegisterCloses(final List<RegisterClose> registerCloses) {
    // this.registerCloses = registerCloses;
    // }

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
        return "DailyClose [id=" + id + ", bookedAt=" + bookedAt + ", cashRegisterTotal=" + registeredTotal
                + ", closeDay=" + closeDay + ", createdAt=" + createdAt + ", incomeTotal=" + incomeTotal + ", posTotal="
                + posTotal + ", bookedBy=" + bookedBy + ", createdBy=" + createdBy + ", dailyCloseDetails="
                + dailyCloseDetails + ", employeeShifts=" + employeeShifts + ", registerCloses=" + registerCloses + "]";
    }

}