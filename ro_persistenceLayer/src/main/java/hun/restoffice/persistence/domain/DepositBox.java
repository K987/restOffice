package hun.restoffice.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    @SequenceGenerator(name = "DEPOSIT_BOX_DEPOSIT_BOX_ID_GENERATOR", sequenceName = "DEPOSIT_BOX_DEPOSIT_BOX_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPOSIT_BOX_DEPOSIT_BOX_ID_GENERATOR")
    @Column(name = "deposit_box_id", updatable = false)
    private Long id;

    @Column(name = "description_txt", length = 500)
    private String description;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "deposit_box_type_cd", nullable = false, updatable = false)
    private DepositBoxTypes type;

    // bi-directional many-to-one association to DepositBoxXExpense
    @OneToMany(mappedBy = "depositBox", fetch = FetchType.LAZY)
    private List<Expense> expenses = new ArrayList<>();

    // bi-directional many-to-one association to DepositBoxXIncome
    @OneToMany(mappedBy = "depositBox", fetch = FetchType.LAZY)
    private List<Income> incomes = new ArrayList<>();

    // bi-directional many-to-one association to Transition
    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    private List<Transition> fromTranistions = new ArrayList<>();

    // bi-directional many-to-one association to Transition
    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    private List<Transition> toTransitions = new ArrayList<>();

    public DepositBox() {
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Expense addExpense(final Expense expense) {
        getExpenses().add(expense);
        expense.setDepositBox(this);

        return expense;
    }

    public Expense removeExpense(final Expense expens) {
        getExpenses().remove(expens);
        expens.setDepositBox(null);

        return expens;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public Income addIncome(final Income income) {
        getIncomes().add(income);
        income.setDepositBox(this);

        return income;
    }

    public Income removeIncome(final Income income) {
        getIncomes().remove(income);
        income.setDepositBox(null);

        return income;
    }

    public List<Transition> getFromTranistions() {
        return fromTranistions;
    }

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

    /**
     * @return the type
     */
    public DepositBoxTypes getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(final DepositBoxTypes type) {
        this.type = type;
    }

    /**
     * @param id
     *            the id to set
     */
    protected void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param expenses
     *            the expenses to set
     */
    protected void setExpenses(final List<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * @param incomes
     *            the incomes to set
     */
    protected void setIncomes(final List<Income> incomes) {
        this.incomes = incomes;
    }

    /**
     * @param fromTranistions
     *            the fromTranistions to set
     */
    protected void setFromTranistions(final List<Transition> fromTranistions) {
        this.fromTranistions = fromTranistions;
    }

    /**
     * @param toTransitions
     *            the toTransitions to set
     */
    protected void setToTransitions(final List<Transition> toTransitions) {
        this.toTransitions = toTransitions;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DepositBox [id=" + id + ", description=" + description + ", name=" + name + ", expenses=" + expenses
                + ", incomes=" + incomes + ", fromTranistions=" + fromTranistions + ", toTransitions=" + toTransitions
                + "]";
    }

}