/**
 * 
 */
package hun.restoffice.mq.listener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.facade.FinanceFacadeLocal;
import hun.restoffice.remoteClient.domain.IncomeStub;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 * 
 *
 * @author kalmankostenszky
 */
@MessageDriven(name = "IncomeListener", activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "incomequeue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class IncomeListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(IncomeListener.class);

	@EJB
	private FinanceFacadeLocal fFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */

	@Override
	public void onMessage(Message message) {
		LOG.info("IcomeListener#onMessage invoked messageId");

		if (message instanceof javax.jms.ObjectMessage) {
			ObjectMessage oMessage = (ObjectMessage) message;
			IncomeStub stub = null;
			try {
				if (oMessage.getObject() instanceof hun.restoffice.remoteClient.domain.IncomeStub) {
					stub = (IncomeStub) oMessage.getObject();
				}
			} catch (JMSException e) {
				LOG.error(e);
			}

			if (stub != null) {
				try {
					this.fFacade.addIncome(stub);
				} catch (FacadeException e) {
					LOG.error(e);
				}
			}
		}

	}
}
