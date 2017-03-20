/**
 * 
 */
package hun.restoffice.mq.eventHandler;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

import hun.restoffice.ejbservice.event.EndOfDayEvent;
import hun.restoffice.mq.publisher.PublisherLocal;

/**
 *  
 *
 * @author kalmankostenszky
 */
public class EndOfDayEventHandler implements Serializable {

	@EJB
	private PublisherLocal publisher;
	
	public void handle(@Observes EndOfDayEvent event){
		this.publisher.publish(event.incomes);
		
	}
}
