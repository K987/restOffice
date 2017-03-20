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
 * The persistent class for the inc_types database table.
 * 
 * @author kalmankostenszky
 */
@Entity
@Table(name = "inc_types")
@NamedQueries(value = { 
		@NamedQuery(name = IncType.FIND_ALL, query = "SELECT i FROM  IncType i"),
		@NamedQuery(name = IncType.FIND_BY_NAME, query = "SELECT i FROM IncType i WHERE LOWER(i.name)=:"+IncType.NAME)
})
public class IncType implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_NAME = "IncType.findByName";
	public static final String FIND_ALL = "IncType.findAll";

	public static final String NAME = "name";

	// fields
	@Id
	@SequenceGenerator(name = "INC_TYPES_INCTYPEID_GENERATOR", sequenceName = "INC_TYPES_INC_TYPE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INC_TYPES_INCTYPEID_GENERATOR")
	@Column(name = "inc_type_id")
	private Integer id;

	// TODO: set this unique in db
	@Column(name = "inc_type_name", unique = true, nullable = false, length = 100)
	private String name;

	@Column(name = "inc_type_prod_related", nullable = false)
	private Boolean prodRelated;

	// bi-directional many-to-one association to Income
	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, targetEntity = Income.class)
	private Set<Income> incomes;

	// constructors
	public IncType() {
	}

	// public methods
	public Income addIncome(Income income) {
		getIncomes().add(income);
		income.setIncType(this);

		return income;
	}

	public Income removeIncome(Income income) {
		getIncomes().remove(income);
		income.setIncType(null);

		return income;
	}

	// getters setters
	/**
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getProdRelated() {
		return this.prodRelated;
	}

	public void setProdRelated(Boolean prodRelated) {
		this.prodRelated = prodRelated;
	}

	public Set<Income> getIncomes() {
		return this.incomes;
	}

	public void setIncomes(Set<Income> incomes) {
		this.incomes = incomes;
	}

}