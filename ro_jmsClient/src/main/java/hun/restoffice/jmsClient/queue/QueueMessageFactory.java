/**
 * 
 */
package hun.restoffice.jmsClient.queue;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.remoteClient.domain.IncomeStub;

/**
 *  
 *
 * @author kalmankostenszky
 */
public class QueueMessageFactory {
	
	private static final Logger LOG = Logger.getLogger(QueueMessageFactory.class);

	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String PROVIDER_URL = "remote://localhost:4447";
	
	private static final String USERNAME = "jmstestuser";
	private static final String PASSWORD = "User#70365";
	
	private final Context context;
	private final String destinationAddress;

	public QueueMessageFactory(QueueName destination) throws NamingException{
		LOG.info("QueueMessageFactory construcor invoked");
		this.destinationAddress = destination.getAddress();
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, QueueMessageFactory.INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, QueueMessageFactory.PROVIDER_URL);
		env.put(Context.SECURITY_PRINCIPAL, QueueMessageFactory.USERNAME);
		env.put(Context.SECURITY_CREDENTIALS, QueueMessageFactory.PASSWORD);
		this.context = new InitialContext(env);
		LOG.info("QueueMessageFactory construcor succeded");
	}
	
	public void sendIncomeMessage(IncomeStub stub){
		LOG.info("QueueMessageFactory#sendIncomeMessage invoked");
		Connection connection = null;
		try{
			ConnectionFactory connectionFactory = (ConnectionFactory) this.context.lookup(QueueMessageFactory.CONNECTION_FACTORY);
			Destination destination = (Destination) this.context.lookup(this.destinationAddress);
			
			connection = connectionFactory.createConnection(QueueMessageFactory.USERNAME, QueueMessageFactory.PASSWORD);
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			connection.start();
			ObjectMessage objectMessage = session.createObjectMessage(stub);	
			producer.send(objectMessage);
			connection.stop();
		} catch (Exception e){
			LOG.error(e);
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (JMSException e) {
					LOG.error(e);
				}
		}
		LOG.info("QueueMessageFactory#sendIncomeMessage succeded");

	}
	
	@Override
	protected void finalize() throws Throwable {
		if (this.context != null) {
			this.context.close();
		}
	}
	
}
