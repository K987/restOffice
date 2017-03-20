/**
 * 
 */
package hun.restoffice.persistence.exception;

/**
 * General exception class for persistence layer
 *
 * @author kalmankostenszky
 */
public class PersistenceServiceException extends Exception {


	private static final long serialVersionUID = 1L;

	private PersistenceExceptionType type;
	// constructors
	public PersistenceServiceException(PersistenceExceptionType type, String description, Exception e) {
		// TODO Auto-generated constructor stub
		super(description, e);
		this.type = type;
	}


	public PersistenceServiceException(PersistenceExceptionType type, String description) {
		super(description);
		this.type = type;

	}

	/**
	 * @return the type
	 */
	public PersistenceExceptionType getType() {
		return type;
	}

}
