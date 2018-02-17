package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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

import hun.restoffice.persistence.type.IncomeFormType;


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

    @Column(name="in_register_flg", nullable=false)
    private Boolean inRegister;

    @Column(name="income_amt", nullable=false, precision=12, scale=2)
    private BigDecimal incomeTotal;

    //bi-directional many-to-one association to DailyClose
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "daily_close_id", nullable = false, updatable = false)
    private DailyClose dailyClose;

    //uni-directional many-to-one association to Income
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="income_id")
    private Income income;

    //uni-directional many-to-one association to IncomeFormType
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="income_form_type_id", nullable=false)
    private IncomeFormType incomeForm;

    public DailyCloseDetail() {
    }

    public long getId() {
        return id;
    }

    public Boolean getInRegister() {
        return inRegister;
    }

    public void setInRegister(final Boolean inRegisterFlg) {
        inRegister = inRegisterFlg;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(final BigDecimal incomeAmt) {
        incomeTotal = incomeAmt;
    }

    public DailyClose getDailyClose() {
        return dailyClose;
    }

    public void setDailyClose(final DailyClose dailyClose) {
        this.dailyClose = dailyClose;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(final Income income) {
        this.income = income;
    }

    public IncomeFormType getIncomeFormType() {
        return incomeForm;
    }

    public void setIncomeFormType(final IncomeFormType incomeFormType) {
        incomeForm = incomeFormType;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DailyCloseDetail [id=" + id + ", inRegister=" + inRegister + ", incomeTotal=" + incomeTotal
                + ", dailyClose=" + dailyClose + ", income=" + income + ", incomeFormType=" + incomeForm + "]";
    }

}