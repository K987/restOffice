/**
 * 
 */
package hun.restoffice.jmsClient.topic;

/**
 *  
 *
 * @author kalmankostenszky
 */
public enum TopicName {

	TOPIC_DAILY_INCOME("jms/queue/dailyincometopic");
	
	private String address;

	private TopicName(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}
}
