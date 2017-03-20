/**
 * 
 */
package hun.restoffice.client.service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import hun.restoffice.remoteClient.facade.DailyTransactionFacadeRemote;
import hun.restoffice.remoteClient.facade.RegisterFacadeRemote;
import hun.restoffice.remoteClient.facade.ShiftFacadeRemote;

/**
 *  Class for remote EJB calls
 *
 * @author kalmankostenszky
 */
public class RemoteServiceFactory {
	
	private static final String JBOSS_INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String JBOSS_PROVIDER_URL = "remote://localhost:4447";
	private static final String JBOSS_URL_PKG_PREFIXES = "org.jboss.ejb.client.naming";

	private static final String JBOSS_NAMING_CLIENT_EJB_CONTEXT_KEY = "jboss.naming.client.ejb.context";
	private static final String JBOSS_NAMING_CLIENT_EJB_CONTEXT_VALUE = "true";
	
	private static Context context;
	
	/**
	 * initializes remote connections
	 * @throws NamingException
	 */
	private static void initContext() throws NamingException{
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_INITIAL_CONTEXT_FACTORY);
		jndiProperties.put(Context.PROVIDER_URL, JBOSS_PROVIDER_URL);
		jndiProperties.put(Context.URL_PKG_PREFIXES, JBOSS_URL_PKG_PREFIXES);
		jndiProperties.put(JBOSS_NAMING_CLIENT_EJB_CONTEXT_KEY, JBOSS_NAMING_CLIENT_EJB_CONTEXT_VALUE);
		context = new InitialContext(jndiProperties);
	}
	
	/**
	 * looks up Shift service
	 * @return
	 * @throws NamingException
	 */
	public static ShiftFacadeRemote lookupShift() throws NamingException {
		if (context == null)
			initContext();
		return (ShiftFacadeRemote) context.lookup("restoffice/ro_ejbLayer/ShiftFacade!hun.restoffice.remoteClient.facade.ShiftFacadeRemote");
		// return (...) context.lookup("diskstore/ds-ejbservice/DiskFacadeImpl!hu.qwaevisz.diskstore.ejbserviceclient.DiskFacadeRemote");
	}

	/**
	 * looks up Register service
	 * @return
	 * @throws NamingException
	 */
	public static RegisterFacadeRemote lookupRegister() throws NamingException{
		if (context == null)
			initContext();
		return (RegisterFacadeRemote) context.lookup("restoffice/ro_ejbLayer/RegisterFacade!hun.restoffice.remoteClient.facade.RegisterFacadeRemote");
	}

	/**
	 * @return
	 */
	public static DailyTransactionFacadeRemote lookupDailyTransaction() throws NamingException{
		if (context == null)
			initContext();
		return (DailyTransactionFacadeRemote) context.lookup("restoffice/ro_ejbLayer/DailyTransactionFacade!hun.restoffice.remoteClient.facade.DailyTransactionFacadeRemote");
	}
	

		
}
