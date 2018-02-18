package hun.restoffice.persistence.entity;

import java.io.Serializable;

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
 * The persistent class for the daily_close_detail database table.
 *
 */
@Entity
@Table(name="daily_close_detail")
@NamedQuery(name="DailyCloseDetail.findAll", query="SELECT d FROM DailyCloseDetail d")
public class DailyCloseDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="DAILY_CLOSE_DETAIL_DAILYCLOSEDETAILID_GENERATOR", sequenceName="DAILY_CLOSE_DETAIL_DAILY_CLOSE_DETAIL_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DAILY_CLOSE_DETAIL_DAILYCLOSEDETAILID_GENERATOR")
    @Column(name = "daily_close_detail_id", updatable = false)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_form_type_id", nullable = false)
    private IncomeType meanOfIncome;

    private double registeredAmt;

    private double notRegisteredAmt;

    @Column(name = "income_amt", nullable = false, precision = 12, scale = 2)
    private double totalAmt;

    // @Column(name="in_register_flg", nullable=false)
    // private Boolean inRegister;

    //bi-directional many-to-one association to DailyClose
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "daily_close_id", nullable = false, updatable = false)
    private DailyClose dailyClose;

    // //uni-directional many-to-one association to Income
    // @ManyToOne(fetch = FetchType.LAZY, optional = true)
    // @JoinColumn(name="income_id")
    // private Income income;


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
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * @return the meanOfIncome
     */
    public IncomeType getMeanOfIncome() {
        return meanOfIncome;
    }

    /**
     * @param meanOfIncome
     *            the meanOfIncome to set
     */
    public void setMeanOfIncome(final IncomeType meanOfIncome) {
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
    public void setTotalAmt(final double totalAmt) {
        this.totalAmt = totalAmt;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DailyCloseDetail [id=" + id + ", meanOfIncome=" + meanOfIncome + ", registeredAmt=" + registeredAmt
                + ", notRegisteredAmt=" + notRegisteredAmt + ", totalAmt=" + totalAmt + ", dailyClose=" + dailyClose
                + "]";
    }


}