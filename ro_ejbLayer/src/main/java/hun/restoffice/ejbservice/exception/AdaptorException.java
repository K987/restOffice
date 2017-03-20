/**
 * 
 */
package hun.restoffice.ejbservice.exception;

/**
 * Exception class for REST callers
 * 
 */
public class AdaptorException extends Exception {


	private static final long serialVersionUID = -7350719779321181727L;
	
	private final ApplicationError error;
	private final String field;

	public AdaptorException(ApplicationError error, String message) {
		this(error, message, null);
	}

	public AdaptorException(ApplicationError error, String message, String field) {
		this(error, message, null, field);
	}

	public AdaptorException(ApplicationError error, String message, Throwable cause, String field) {
		super(message, cause);
		this.error = error;
		this.field = field;
	}

	public ApplicationError getErrorCode() {
		return this.error;
	}

	public ErrorStub build() {
		return this.error.build(this.field);
	}

}
