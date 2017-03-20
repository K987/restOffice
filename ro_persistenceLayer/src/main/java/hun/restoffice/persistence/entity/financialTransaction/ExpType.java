package hun.restoffice.persistence.entity.financialTransaction;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the exp_types database table.
 * 
 */
/**
 * @author kalmankostenszky
 *
 */
@Entity
@Table(name = "exp_types")
@NamedQueries({ 
		@NamedQuery(name = ExpType.FIND_ALL, query = "SELECT e FROM ExpType e"),
		@NamedQuery(name = ExpType.FIND_BY_NAME, query = "SELECT e FROM ExpType e WHERE LOWER(e.name)=:"+ExpType.NAME)
})
public class ExpType implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "ExpType.findAll";

	public static final String FIND_BY_NAME = "ExpType.findByName";

	public static final String NAME = "name";

	// fields
	@Id
	@SequenceGenerator(name = "EXP_TYPES_EXPTYPEID_GENERATOR", sequenceName = "EXP_TYPES_EXP_TYPE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXP_TYPES_EXPTYPEID_GENERATOR")
	@Column(name = "exp_type_id")
	private Integer id;

	// TODO: set this to unique in the database as well
	@Column(name = "exp_type_name", nullable = false, unique = true, length = 100)
	private String name;

	@Column(name = "exp_type_prod_related", nullable = false)
	private Boolean prodRelated;

	// bi-directional many-to-one association to Expense
	@OneToMany(mappedBy = "expType", fetch = FetchType.LAZY, targetEntity = Expense.class)
	private Set<Expense> expenses;

	// constructors
	public ExpType() {
	}

	// public methods

	/**
	 * @param expense
	 * @return
	 */
	public Expense addExpense(Expense expense) {
		getExpenses().add(expense);
		expense.setExpType(this);

		return expense;
	}

	/**
	 * @param expense
	 * @return
	 */
	public Expense removeExpense(Expense expense) {
		getExpenses().remove(expense);
		expense.setExpType(null);

		return expense;
	}

	// getters setters
	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setExpTypeName(String name) {
		this.name = name;
	}

	public Boolean getProdRelated() {
		return this.prodRelated;
	}

	public void setProdRelated(Boolean prodRelated) {
		this.prodRelated = prodRelated;
	}

	public Set<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

}