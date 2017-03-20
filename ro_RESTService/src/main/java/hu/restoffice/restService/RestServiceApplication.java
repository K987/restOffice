/**
 * 
 */
package hu.restoffice.restService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Entry class for rest services
 *
 * @author kalmankostenszky
 */
@ApplicationPath("/rest")
public class RestServiceApplication extends Application {
	/*
	@Override
	public Set<Object> getSingletons(){
		HashSet<Object> singletons = new HashSet<>();
		singletons.add(new JacksonJaxbJsonProvider());
		return singletons;
	}
	*/
	
}
