/**
 * 
 */
package hun.restoffice.jmsClient.queue;

/**
 *  
 *
 * @author kalmankostenszky
 */
public enum QueueName {

	DESTINATION_INCOME("jms/queue/incomequeue"),
	DESTINATION_DAILY_INCOME("jms/queue/dailyincomequeue");
	
	private String address;

	private QueueName(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}
}
