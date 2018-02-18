package hun.restoffice.persistence.param;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hun.restoffice.persistence.util.TemporalValidity;


/**
 * The persistent class for the param_income_form_x_deposit_box database table.
 *
 */
@Entity
@Table(name="param_income_form_x_deposit_box")
@NamedQuery(name="ParamIncomeFormXDepositBox.findAll", query="SELECT p FROM ParamIncomeFormXDepositBox p")
public class Obsolete_ParamIncomeFormXDepositBox implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PARAM_INCOME_FORM_X_DEPOSIT_BOX_INCOMEFORMXDEPOSITBOXID_GENERATOR", sequenceName="PARAM_INCOME_FORM_X_DEPOSIT_BOX_INCOME_FORM_X_DEPOSIT_BOX_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PARAM_INCOME_FORM_X_DEPOSIT_BOX_INCOMEFORMXDEPOSITBOXID_GENERATOR")
    @Column(name="income_form_x_deposit_box_id", unique=true, nullable=false)
    private Integer incomeFormXDepositBoxId;

    @Column(name="deposit_box_id", nullable=false)
    private Integer depositBoxId;

    @Column(name="income_form_id", nullable=false)
    private Integer incomeFormId;

    @Column(name="modification_status_cd", nullable=false)
    private Boolean modificationStatusCd;

    @Temporal(TemporalType.DATE)
    @Column(name="valid_from_dttm", nullable=false)
    private Date validFromDttm;

    @Temporal(TemporalType.DATE)
    @Column(name="valid_to_dttm", nullable=false)
    private Date validToDttm;

    @Embedded
    private TemporalValidity validity;

    public Obsolete_ParamIncomeFormXDepositBox() {
    }

    public Integer getIncomeFormXDepositBoxId() {
        return incomeFormXDepositBoxId;
    }

    public void setIncomeFormXDepositBoxId(final Integer incomeFormXDepositBoxId) {
        this.incomeFormXDepositBoxId = incomeFormXDepositBoxId;
    }

    public Integer getDepositBoxId() {
        return depositBoxId;
    }

    public void setDepositBoxId(final Integer depositBoxId) {
        this.depositBoxId = depositBoxId;
    }

    public Integer getIncomeFormId() {
        return incomeFormId;
    }

    public void setIncomeFormId(final Integer incomeFormId) {
        this.incomeFormId = incomeFormId;
    }

    public Boolean getModificationStatusCd() {
        return modificationStatusCd;
    }

    public void setModificationStatusCd(final Boolean modificationStatusCd) {
        this.modificationStatusCd = modificationStatusCd;
    }

    public Date getValidFromDttm() {
        return validFromDttm;
    }

    public void setValidFromDttm(final Date validFromDttm) {
        this.validFromDttm = validFromDttm;
    }

    public Date getValidToDttm() {
        return validToDttm;
    }

    public void setValidToDttm(final Date validToDttm) {
        this.validToDttm = validToDttm;
    }

    public TemporalValidity getValidity() {
        return validity;
    }

}