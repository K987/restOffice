/**
 * 
 */
package hun.restoffice.jmsClient.topic;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import hun.restoffice.remoteClient.domain.IncomeStub;

/**
 * 
 *
 * @author kalmankostenszky
 */
public class IncomeListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(IncomeListener.class);

	private final List<IncomeStub> incomes;

	/**
		* 
		*/
	public IncomeListener() {
		incomes = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof javax.jms.ObjectMessage) {
			ObjectMessage om = (ObjectMessage) message;
			try {
				if (om.getObject() instanceof hun.restoffice.remoteClient.domain.IncomeStub) {
					IncomeStub stub = (IncomeStub) om.getObject();
					LOG.info("new message recieved: " + stub.getDocId());
					incomes.add(stub);
				}
			} catch (JMSException e) {
				LOG.error(e.getLocalizedMessage());
			}
		}
	}

	public void printAll() {
		for (IncomeStub incomeStub : incomes) {
			System.out.println(incomeStub.toString());
		}
	}
}
