/**
 * 
 */
package hun.restoffice.ejbservice.domain;

/**
 *  
 *
 * @author kalmankostenszky
 */
public class ExpenseTypeStub {

	private final Integer id;
	private final String name;
	private final Boolean prodRelated;

	/**
	 * @param id
	 * @param name
	 * @param prodRelated
	 */
	public ExpenseTypeStub(Integer id, String name, Boolean prodRelated) {
		this.id = id;
		this.name = name;
		this.prodRelated = prodRelated;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the prodRelated
	 */
	public Boolean getProdRelated() {
		return prodRelated;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ExpenseTypeStub [id=%s, name=%s, prodRelated=%s]", id, name, prodRelated);
	}

	
}
