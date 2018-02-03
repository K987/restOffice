/**
 * 
 */
package hun.restoffice.ejbservice.domain;

/**
 *  
 *
 * @author kalmankostenszky
 */
public enum PaymentMethodStub {
	
	CASH("készpénz"),
	TRANSFER("átutalás"),
	DEFFERED_CASH("halasztott készpénz"),
	DEBT_CARD("hitelkártya");
	
	private final String description;

	private PaymentMethodStub(String description){
		this.description = description;
		
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
