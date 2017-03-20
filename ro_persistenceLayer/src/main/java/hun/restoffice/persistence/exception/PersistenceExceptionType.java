/**
 * 
 */
package hun.restoffice.persistence.exception;

/**
 * Persistence exception types
 *
 * @author kalmankostenszky
 */
public enum PersistenceExceptionType {

	// default errors
	NOT_EXISTS(-10, "the required entity not esxists"),
	AMBIGOUS_RESULT(-20, "more results found than expected"),
	UNKNOWN(-99, "unknown exception happend"),
	EXISTS_ALREADY(-30, "entity already exists");

	// fields
	private final int code;
	private final String message;

	// constructor
	private PersistenceExceptionType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	// getters setters

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
