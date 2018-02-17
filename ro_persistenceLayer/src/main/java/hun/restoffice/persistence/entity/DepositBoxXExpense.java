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
@Table(name="deposit_box_x_expense")
@NamedQuery(name="DepositBoxXExpense.findAll", query="SELECT d FROM DepositBoxXExpense d")
public class DepositBoxXExpense implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="DEPOSIT_BOX_X_EXPENSE_DEPOSITBOXXEXPENSEID_GENERATOR", sequenceName="DEPOSIT_BOX_X_EXPENSE_DEPOSIT_BOX_X_EXPENSE_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPOSIT_BOX_X_EXPENSE_DEPOSITBOXXEXPENSEID_GENERATOR")
    @Column(name="deposit_box_x_expense_id", updatable=false, unique=true, nullable=false)
    private Long id;

    @Column(name = "realized_on_dttm")
    private LocalDateTime realizedOn;

    //bi-directional many-to-one association to DepositBox
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "deposit_box_id", nullable = false, updatable = false)
    private DepositBox depositBox;

    //uni-directional many-to-one association to Expense
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false, updatable = false)
    private Expense expense;

    public DepositBoxXExpense() {
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getRealizedOn() {
        return realizedOn;
    }

    public void setRealizedOn(final LocalDateTime relaizedDttm) {
        realizedOn = relaizedDttm;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DepositBoxXExpense [id=" + id + ", realizedOn=" + realizedOn + ", depositBox=" + depositBox
                + ", expense=" + expense + "]";
    }

}