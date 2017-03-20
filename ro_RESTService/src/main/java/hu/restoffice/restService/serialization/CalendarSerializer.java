/**
 * 
 */
package hu.restoffice.restService.serialization;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 *  Calendar type serializer for JSON
 *
 * @author kalmankostenszky
 */
public class CalendarSerializer extends JsonSerializer<Calendar> {

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Calendar value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		String date = df.format(value.getTime());
		jgen.writeString(date);
	}

}
