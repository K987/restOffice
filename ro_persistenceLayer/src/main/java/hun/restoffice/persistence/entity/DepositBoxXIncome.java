package hun.restoffice.persistence.entity;

import java.io.Serializable;
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
 * The persistent class for the deposit_box_x_income database table.
 *
 */
@Entity
@Table(name = "deposit_box_x_income")
@NamedQuery(name = "DepositBoxXIncome.findAll", query = "SELECT d FROM DepositBoxXIncome d")
public class DepositBoxXIncome implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "DEPOSIT_BOX_X_INCOME_DEPOSITBOXXINCOMEID_GENERATOR", sequenceName = "DEPOSIT_BOX_X_INCOME_DEPOSIT_BOX_X_INCOME_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPOSIT_BOX_X_INCOME_DEPOSITBOXXINCOMEID_GENERATOR")
    @Column(name = "deposit_box_x_income_id", updatable = false)
    private Long id;

    @Column(name = "realized_on_dttm")
    private LocalDateTime realizedOn;

    // bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_box_id", nullable = false, updatable = false)
    private DepositBox depositBox;

    // uni-directional many-to-one association to Income
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_id", nullable = false, updatable = false)
    private Income income;

    public DepositBoxXIncome() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getRealizedOn() {
        return realizedOn;
    }

    public void setRegisteredDttm(final LocalDateTime relaizedDttm) {
        realizedOn = relaizedDttm;
    }

    public DepositBox getDepositBox() {
        return depositBox;
    }

    public void setDepositBox(final DepositBox depositBox) {
        this.depositBox = depositBox;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(final Income income) {
        this.income = income;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DepositBoxXIncome [id=" + id + ", realizedOn=" + realizedOn + ", depositBox=" + depositBox + ", income="
                + income + "]";
    }

}