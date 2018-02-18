package hun.restoffice.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hun.restoffice.persistence.type.PaymentTypes;


/**
 * The persistent class for the deposit_box database table.
 *
 */

// TODO: valahogy bektni a kiadást és a bevételt
@Entity
@Table(name = "deposit_box")
@NamedQuery(name = "DepositBox.findAll", query = "SELECT d FROM DepositBox d")
public class DepositBox implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="DEPOSIT_BOX_DEPOSITBOXID_GENERATOR", sequenceName="DEPOSIT_BOX_DEPOSIT_BOX_ID_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPOSIT_BOX_DEPOSITBOXID_GENERATOR")
    @Column(name = "deposit_box_id", updatable = false)
    private Long id;

    @Column(name = "default_payment_type", unique = true)
    private PaymentTypes defaultPaymentType;

    @Column(name = "allowed_payment_types")
    private Set<PaymentTypes> allowedPayments;

    @Column(name="description_txt", nullable=false, length=500)
    private String description;

    @Column(nullable=false, length=50)
    private String name;

    //bi-directional many-to-one association to DepositBoxXExpense
    @OneToMany(mappedBy = "depositBox", fetch = FetchType.LAZY)
    private List<DepositBoxXExpense> expenses = new ArrayList<>();

    //bi-directional many-to-one association to DepositBoxXIncome
    @OneToMany(mappedBy = "depositBox", fetch = FetchType.LAZY)
    private List<DepositBoxXIncome> incomes = new ArrayList<>();

    //bi-directional many-to-one association to Transition
    @OneToMany(mappedBy = "depositBox1", fetch = FetchType.LAZY)
    private List<Transition> fromTranistions = new ArrayList<>();

    //bi-directional many-to-one association to Transition
    @OneToMany(mappedBy = "depositBox2", fetch = FetchType.LAZY)
    private List<Transition> toTransitions = new ArrayList<>();

    public DepositBox() {
    }

    public Long getId() {
        return id;
    }

    public Integer getDepositBoxType() {
        return defaultPaymentType;
    }

    public void setDepositBoxType(final Integer depositBoxTypeCd) {
        defaultPaymentType = depositBoxTypeCd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String descriptionTxt) {
        description = descriptionTxt;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<DepositBoxXExpense> getExpenses() {
        return expenses;
    }

    // public void setExpenses(final List<DepositBoxXExpense> expenses) {
    // this.expenses = expenses;
    // }

    public DepositBoxXExpense addExpens(final DepositBoxXExpense expens) {
        getExpenses().add(expens);
        expens.setDepositBox(this);

        return expens;
    }

    public DepositBoxXExpense removeExpens(final DepositBoxXExpense expens) {
        getExpenses().remove(expens);
        expens.setDepositBox(null);

        return expens;
    }

    public List<DepositBoxXIncome> getIncomes() {
        return incomes;
    }

    // public void setIncomes(final List<DepositBoxXIncome> incomes) {
    // this.incomes = incomes;
    // }

    public DepositBoxXIncome addIncome(final DepositBoxXIncome income) {
        getIncomes().add(income);
        income.setDepositBox(this);

        return income;
    }

    public DepositBoxXIncome removeIncome(final DepositBoxXIncome income) {
        getIncomes().remove(income);
        income.setDepositBox(null);

        return income;
    }

    public List<Transition> getFromTranistions() {
        return fromTranistions;
    }

    // public void setFromTranistions(final List<Transition> fromTranistions) {
    // this.fromTranistions = fromTranistions;
    // }

    public Transition addFromTranistion(final Transition fromTranistion) {
        getFromTranistions().add(fromTranistion);
        fromTranistion.setFrom(this);

        return fromTranistion;
    }

    public Transition removeFromTranistion(final Transition fromTranistion) {
        getFromTranistions().remove(fromTranistion);
        fromTranistion.setFrom(null);

        return fromTranistion;
    }

    public List<Transition> getToTransitions() {
        return toTransitions;
    }

    // public void setToTransitions(final List<Transition> toTransitions) {
    // this.toTransitions = toTransitions;
    // }

    public Transition addToTransition(final Transition toTransition) {
        getToTransitions().add(toTransition);
        toTransition.setTo(this);

        return toTransition;
    }

    public Transition removeToTransition(final Transition toTransition) {
        getToTransitions().remove(toTransition);
        toTransition.setTo(null);

        return toTransition;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DepositBox [id=" + id + ", depositBoxType=" + defaultPaymentType + ", description=" + description
                + ", name=" + name + ", expenses=" + expenses + ", incomes=" + incomes + ", fromTranistions="
                + fromTranistions + ", toTransitions=" + toTransitions + "]";
    }

}