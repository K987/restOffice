/**
 * 
 */
package hun.restoffice.ejbservice.exception;

/**
 *
 * Application error stub types
 *
 */
public enum ApplicationError {

	UNEXPECTED(-10, 500, "Unexpected error"), // Internal Server Error
	NOT_EXISTS(-44, 404, "Resource not found"), // Not Found
	HAS_DEPENDENCY(-41, 412, "Has dependency"), // Precondition Failed
	UNEXPECTED_RESULT(-44, 409, "Unexpected result"), //Conflict
	EXISTS_ALREADY(-40, 400, "Exists already");// Conflict

	private final int code;
	private final int httpStatusCode;
	private final String message;

	private ApplicationError(int code, int httpStatusCode, String message) {
		this.code = code;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}

	public int getHttpStatusCode() {
		return this.httpStatusCode;
	}

	public ErrorStub build(String field) {
		return new ErrorStub(this.code, this.message, field);
	}

}