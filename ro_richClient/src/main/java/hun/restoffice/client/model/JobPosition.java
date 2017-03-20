/**
 * 
 */
package hun.restoffice.client.model;

/**
 * Employee job positions
 * 
 * @author kalmankostenszky
 */
public enum JobPosition {

	WAITER("felszolgáló"),
	BARTENDER("báros"),
	CHEF("szakács");
	
	private String displayName;

	private JobPosition(String displayName) {
		this.displayName = displayName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.displayName;
	}
}
