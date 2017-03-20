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
 * The persistent class for the cost_centers database table.
 * 
 * @author kalmankostenszky
 *
 */
@Entity
@Table(name = "cost_centers")
@NamedQueries({ 
	@NamedQuery(name = CostCenter.FIND_ALL, query = "SELECT c FROM CostCenter c"),
	@NamedQuery(name = CostCenter.FIND_BY_NAME, query = "SELECT c FROM CostCenter c WHERE LOWER(c.name) =:"+CostCenter.NAME)
})
public class CostCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "CostCenter.findAll";
	public static final String FIND_BY_NAME = "CostCenter.findByName";

	public static final String NAME = "name";

	// fields
	@Id
	@SequenceGenerator(name = "COST_CENTERS_COSTCENTERID_GENERATOR", sequenceName = "COST_CENTERS_COST_CENTER_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COST_CENTERS_COSTCENTERID_GENERATOR")
	@Column(name = "cost_center_id")
	private Integer id;

	@Column(name = "cost_center_default", nullable = false)
	private Boolean defaultCostCenter;

	// TODO: set this to unique in the database as well
	@Column(name = "cost_center_name", nullable = false, unique = true, length = 100)
	private String name;

	// bi-directional many-to-one association to Expense
	@OneToMany(mappedBy = "costCenter", fetch = FetchType.LAZY, targetEntity = Expense.class)
	private Set<Expense> expenses;

	// constructors

	/**
	 * 
	 */
	public CostCenter() {
	}

	// public methods

	/**
	 * set an expense to this cost center
	 * 
	 * @param expense
	 *            to be added
	 * @return the added expense
	 */
	public Expense addExpense(Expense expense) {
		getExpenses().add(expense);
		expense.setCostCenter(this);

		return expense;
	}

	/**
	 * Remove an expense from cost center
	 * 
	 * @param expense
	 *            to be removed
	 * @return removed expense
	 */
	public Expense removeExpense(Expense expense) {
		getExpenses().remove(expense);
		expense.setCostCenter(null);

		return expense;
	}

	// getters and setters

	public Integer getId() {
		return this.id;
	}

	public Boolean isDefault() {
		return this.defaultCostCenter;
	}

	// TODO: if true set this cost center to default and set previous default to false
	public void setDefault(Boolean costCenterDefault) {

		this.defaultCostCenter = costCenterDefault;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String costCenterName) {
		this.name = costCenterName;
	}

	public Set<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

}