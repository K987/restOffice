package hun.restoffice.persistence.domain;

import java.io.Serializable;
import java.time.LocalDate;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the register_close database table.
 *
 */
@Entity
@Table(name = "register_close")
@NamedQuery(name = "RegisterClose.findAll", query = "SELECT r FROM RegisterClose r")
public class RegisterClose implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "REGISTER_CLOSE_REGISTER_CLOSE_ID_GENERATOR", sequenceName = "REGISTER_CLOSE_REGISTER_CLOSE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGISTER_CLOSE_REGISTER_CLOSE_ID_GENERATOR")
    @Column(name = "register_close_id", updatable = false)
    private Long id;

    @Column(name = "close_amt", nullable = false, precision = 12, scale = 2)
    private double closeAmt;

    @Column(name = "close_dt", nullable = false)
    private LocalDate closeDate;

    @Column(name = "close_no", nullable = false)
    private Long closeNo;

    // bi-directional many-to-one association to DailyClose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_close_id")
    private DailyClose dailyClose;

    // uni-directional many-to-one association to Register
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_id", nullable = false)
    private Register register;

    @Embedded
    private UserAction action;

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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RegisterClose [id=" + id + ", closeAmt=" + closeAmt + ", closeDate=" + closeDate + ", closeNo="
                + closeNo + ", dailyClose=" + dailyClose + ", register=" + register + ", action=" + action + "]";
    }
}