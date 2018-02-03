
package hun.restoffice.ejbservice.exception;

/**
 * Exception of facade
 * @author kalmankostenszky
 */
//TODO: make error codes
public class FacadeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param localizedMessage
	 */
	public FacadeException(String localizedMessage) {
		super(localizedMessage);
		
	}

}
