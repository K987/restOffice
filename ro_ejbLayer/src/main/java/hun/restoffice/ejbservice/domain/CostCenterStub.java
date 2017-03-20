/**
 * 
 */
package hun.restoffice.ejbservice.domain;

/**
 *  
 *
 * @author kalmankostenszky
 */
public class CostCenterStub {

	private final Integer id;
	private final String name;

	/**
	 * @param id
	 * @param name
	 */
	public CostCenterStub(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CostCenterStub [id=%s, name=%s]", id, name);
	}
	
	

}
