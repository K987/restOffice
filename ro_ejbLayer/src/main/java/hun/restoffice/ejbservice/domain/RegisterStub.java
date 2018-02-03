/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.io.Serializable;

/**
 *  DTO for register
 *
 * @author kalmankostenszky
 */
public class RegisterStub implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String registerId;
	private final int registerType;

	/**
	 * @param registerType
	 * @param ordinal
	 */
	public RegisterStub(String registerId, int registerType) {
		this.registerId = registerId;
		this.registerType = registerType;
	}

	/**
	 * @return the registerId
	 */
	public String getRegisterId() {
		return registerId;
	}

	/**
	 * @return the registerType
	 */
	public int getRegisterType() {
		return registerType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("RegisterStub [registerId=%s, registerType=%s]", registerId, registerType);
	}

	
}
