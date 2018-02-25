package hun.restoffice.persistence.domain;

import java.io.Serializable;

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
 * The persistent class for the daily_close_detail database table.
 *
 */
@Entity
@Table(name = "daily_close_detail")
@NamedQuery(name = "DailyCloseDetail.findAll", query = "SELECT d FROM DailyCloseDetail d")
public class DailyCloseDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "DAILY_CLOSE_DETAIL_DAILY_CLOSE_DETAIL_ID_GENERATOR", sequenceName = "DAILY_CLOSE_DETAIL_DAILY_CLOSE_DETAIL_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DAILY_CLOSE_DETAIL_DAILY_CLOSE_DETAIL_ID_GENERATOR")
    @Column(name = "daily_close_detail_id", updatable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_form_category_id", nullable = false)
    private IncomeFormCategory meanOfIncome;

    @Column(name = "registered_amt")
    private double registeredAmt;

    @Column(name = "not_registered_amt")
    private double notRegisteredAmt;

    @Column(name = "income_amt", nullable = false, precision = 12, scale = 2)
    private double totalAmt;

    // bi-directional many-to-one association to DailyClose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_close_id", nullable = false, updatable = false)
    private DailyClose dailyClose;

    @Embedded
    private UserAction action;

    public DailyCloseDetail() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final long id) {
        this.id = id;
    }

    /**
     * @return the meanOfIncome
     */
    public IncomeFormCategory getMeanOfIncome() {
        return meanOfIncome;
    }

    /**
     * @param meanOfIncome
     *            the meanOfIncome to set
     */
    public void setMeanOfIncome(final IncomeFormCategory meanOfIncome) {
        this.meanOfIncome = meanOfIncome;
    }

    /**
     * @return the registeredAmt
     */
    public double getRegisteredAmt() {
        return registeredAmt;
    }

    /**
     * @param registeredAmt
     *            the registeredAmt to set
     */
    public void setRegisteredAmt(final double registeredAmt) {
        this.registeredAmt = registeredAmt;
        setTotalAmt();
    }

    /**
     * @return the notRegisteredAmt
     */
    public double getNotRegisteredAmt() {
        return notRegisteredAmt;
    }

    /**
     * @param notRegisteredAmt
     *            the notRegisteredAmt to set
     */
    public void setNotRegisteredAmt(final double notRegisteredAmt) {
        this.notRegisteredAmt = notRegisteredAmt;
        setTotalAmt();
    }

    /**
     * @return the totalAmt
     */
    public double getTotalAmt() {
        return totalAmt;
    }

    /**
     * @param totalAmt
     *            the totalAmt to set
     */
    private void setTotalAmt() {
        totalAmt = registeredAmt + notRegisteredAmt;
    }

    /**
     * @return the dailyClose
     */
    public DailyClose getDailyClose() {
        return dailyClose;
    }

    /**
     * @param dailyClose
     *            the dailyClose to set
     */
    public void setDailyClose(final DailyClose dailyClose) {
        this.dailyClose = dailyClose;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DailyCloseDetail [id=" + id + ", meanOfIncome=" + meanOfIncome + ", registeredAmt=" + registeredAmt
                + ", notRegisteredAmt=" + notRegisteredAmt + ", totalAmt=" + totalAmt + ", dailyClose=" + dailyClose
                + ", action=" + action + "]";
    }
}