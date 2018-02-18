package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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


/**
 * The persistent class for the register_close database table.
 *
 */
@Entity
@Table(name="register_close")
@NamedQuery(name="RegisterClose.findAll", query="SELECT r FROM RegisterClose r")
public class RegisterClose implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="REGISTER_CLOSE_REGISTERCLOSEID_GENERATOR", sequenceName="REGISTER_CLOSE_REGISTER_CLOSE_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTER_CLOSE_REGISTERCLOSEID_GENERATOR")
    @Column(name = "register_close_id", updatable = false)
    private Long id;

    @Column(name="close_amt", nullable=false, precision=12, scale=2)
    private double closeAmt;

    @Column(name="close_dt", nullable=false)
    private LocalDate closeDate;

    @Column(name="close_no", nullable=false)
    private Long closeNo;

    @Column(name="create_dttm", nullable=false)
    private LocalDateTime createdAt;

    //bi-directional many-to-one association to DailyClose
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="daily_close_id")
    private DailyClose dailyClose;

    //uni-directional many-to-one association to Register
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="register_id", nullable=false)
    private Register register;

    //uni-directional many-to-one association to RoUser
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="create_user_id", nullable=false)
    private User createdUser;

    public RegisterClose() {
    }

    public Long getId() {
        return id;
    }

    public double getCloseAmt() {
        return closeAmt;
    }

    public void setCloseAmt(final double closeAmt) {
        this.closeAmt = closeAmt;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(final LocalDate closeDt) {
        closeDate = closeDt;
    }

    public Long getCloseNo() {
        return closeNo;
    }

    public void setCloseNo(final Long closeNo) {
        this.closeNo = closeNo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createDttm) {
        createdAt = createDttm;
    }

    public DailyClose getDailyClose() {
        return dailyClose;
    }

    public void setDailyClose(final DailyClose dailyClose) {
        this.dailyClose = dailyClose;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(final Register register) {
        this.register = register;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(final User roUser) {
        createdUser = roUser;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RegisterClose [id=" + id + ", closeAmt=" + closeAmt + ", closeDate=" + closeDate + ", closeNo="
                + closeNo + ", createdAt=" + createdAt + ", dailyClose=" + dailyClose + ", register=" + register
                + ", createdUser=" + createdUser + "]";
    }
}