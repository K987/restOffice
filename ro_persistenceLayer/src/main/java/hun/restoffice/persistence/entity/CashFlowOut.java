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
 * The persistent class for the deposit_box_x_expense database table.
 *
 */
@Entity
@Table(name = "cashflow_out")
@NamedQuery(name = "DepositBoxXExpense.findAll", query = "SELECT d FROM DepositBoxXExpense d")
public class CashFlowOut implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "DEPOSIT_BOX_X_EXPENSE_DEPOSITBOXXEXPENSEID_GENERATOR", sequenceName = "DEPOSIT_BOX_X_EXPENSE_DEPOSIT_BOX_X_EXPENSE_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPOSIT_BOX_X_EXPENSE_DEPOSITBOXXEXPENSEID_GENERATOR")
    @Column(name = "cashflow_out", updatable = false, unique = true, nullable = false)
    private Long id;

    @Column(name = "registered_dttm", nullable = false, updatable = false)
    private LocalDateTime registered;

    @Column(name = "accounted_dttm")
    private LocalDateTime accounted;

    // bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_box_id", nullable = false)
    private DepositBox depositBox;

    // uni-directional many-to-one association to Expense
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false, updatable = false)
    private Expense expense;

    public CashFlowOut() {
    }

    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    protected void setRegistered(final LocalDateTime registered) {
        this.registered = registered;
    }

    public DepositBox getDepositBox() {
        return depositBox;
    }

    public void setDepositBox(final DepositBox depositBox) {
        this.depositBox = depositBox;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(final Expense expense) {
        this.expense = expense;
    }

    /**
     * @return the accounted
     */
    public LocalDateTime getAccounted() {
        return accounted;
    }

    /**
     * @param accounted
     *            the accounted to set
     */
    public void setAccounted(final LocalDateTime accounted) {
        this.accounted = accounted;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CashFlowOut [id=" + id + ", registered=" + registered + ", accounted=" + accounted + ", depositBox="
                + depositBox + ", expense=" + expense + "]";
    }
}