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

import hun.restoffice.persistence.type.IncomeType;


/**
 * The persistent class for the income_detail database table.
 *
 */
@Entity
@Table(name="income_detail")
@NamedQuery(name="IncomeDetail.findAll", query="SELECT i FROM IncomeDetail i")
public class IncomeDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="INCOME_DETAIL_INCOMEDETAILID_GENERATOR", sequenceName="INCOME_DETAIL_INCOME_DETAIL_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCOME_DETAIL_INCOMEDETAILID_GENERATOR")
    @Column(name = "income_detail_id", updatable = false)
    private Long id;

    @Column(name="description_txt", nullable=false, length=500)
    private String description;

    @Column(name="income_gross_amt", nullable=false, precision=12, scale=2)
    private BigDecimal incomeGrossAmt;

    @Column(name="income_net_amt", precision=12, scale=2)
    private BigDecimal incomeNetAmt;

    @Column(name="income_vat_pct", precision=2, scale=2)
    private BigDecimal incomeVatPct;

    //bi-directional many-to-one association to Income
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="income_id", nullable=false)
    private Income income;

    //uni-directional many-to-one association to IncomeType
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="income_type_id", nullable=false)
    private IncomeType incomeType;

    public IncomeDetail() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String descriptionTxt) {
        description = descriptionTxt;
    }

    public BigDecimal getIncomeGrossAmt() {
        return incomeGrossAmt;
    }

    public void setIncomeGrossAmt(final BigDecimal incomeGrossAmt) {
        this.incomeGrossAmt = incomeGrossAmt;
    }

    public BigDecimal getIncomeNetAmt() {
        return incomeNetAmt;
    }

    public void setIncomeNetAmt(final BigDecimal incomeNetAmt) {
        this.incomeNetAmt = incomeNetAmt;
    }

    public BigDecimal getIncomeVatPct() {
        return incomeVatPct;
    }

    public void setIncomeVatPct(final BigDecimal incomeVatPct) {
        this.incomeVatPct = incomeVatPct;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(final Income income) {
        this.income = income;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(final IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "IncomeDetail [id=" + id + ", description=" + description + ", incomeGrossAmt=" + incomeGrossAmt
                + ", incomeNetAmt=" + incomeNetAmt + ", incomeVatPct=" + incomeVatPct + ", income=" + income
                + ", incomeType=" + incomeType + "]";
    }

}