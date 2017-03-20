/**
 * 
 */
package hu.restoffice.restService.exception;

/**
 *  REST request errors
 *
 * @author kalmankostenszky
 */
public class RestError {
	private final int code;
	private final String message;
	

	public RestError(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
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
