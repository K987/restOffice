/**
 * 
 */
package hu.restoffice.restService.serialization;

import java.util.Calendar;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 * Serialization config for JSON reponses
 *
 * @author kalmankostenszky
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class SerializationConfig implements ContextResolver<ObjectMapper> {

	private ObjectMapper objectMapper;

	public SerializationConfig() {
		this.objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("RestOffice JSON output serializers", new Version(1, 0, 0, null));
		module.addSerializer(Calendar.class, new CalendarSerializer());
		// here to add more
		objectMapper.registerModule(module);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ContextResolver#getContext(java.lang.Class)
	 */
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return objectMapper;
	}

}
