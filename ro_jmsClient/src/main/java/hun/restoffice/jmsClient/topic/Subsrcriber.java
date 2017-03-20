/**
 * 
 */
package hun.restoffice.jmsClient.topic;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

/**
 * 
 *
 * @author kalmankostenszky
 */
public class Subsrcriber {

	private static final Logger LOG = Logger.getLogger(Subsrcriber.class);

	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String PROVIDER_URL = "remote://localhost:4447";

	private static final String USERNAME = "jmstestuser";
	private static final String PASSWORD = "User#70365";

	TopicConnection connection;
	TopicSession session;
	Topic topic;

	String topicAddress;
	Context context;

	public Subsrcriber(TopicName topicAddress) throws NamingException {

		LOG.info("Subscriber construcor invoked");
		this.topicAddress = topicAddress.getAddress();
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, Subsrcriber.INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, Subsrcriber.PROVIDER_URL);
		env.put(Context.SECURITY_PRINCIPAL, Subsrcriber.USERNAME);
		env.put(Context.SECURITY_CREDENTIALS, Subsrcriber.PASSWORD);
		this.context = new InitialContext(env);
		LOG.info("Subscriber construcor succeded");

	}

	private void setSubscription() {
		LOG.info("setSubscription invoked");
		TopicConnectionFactory tcf = null;
		try {
			tcf = (TopicConnectionFactory) context.lookup(Subsrcriber.CONNECTION_FACTORY);
			topic = (Topic) context.lookup(topicAddress);
		} catch (NamingException e) {
			LOG.error(e);
		}
		LOG.info("context ok");
		try {
			connection = tcf.createTopicConnection(Subsrcriber.USERNAME, Subsrcriber.PASSWORD);
			session = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
			connection.start();
		} catch (JMSException e) {
			LOG.error(e);
		}
		LOG.info("setSubscription finished");
	}

	public void startASync(MessageListener ml) throws JMSException, NamingException {
		LOG.info("startAsync invoked");
		setSubscription();

		TopicSubscriber ts = session.createSubscriber(topic);
		ts.setMessageListener(ml);
		LOG.info("startAsync finished");
	}

	public void stop() throws JMSException {
		connection.stop();
		session.close();
		connection.close();
	}
}
