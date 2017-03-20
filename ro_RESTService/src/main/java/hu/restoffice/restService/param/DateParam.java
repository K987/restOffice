/**
 * 
 */
package hu.restoffice.restService.param;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import hu.restoffice.restService.exception.RestError;

/**
 * String to date - for param type conversion
 *
 * @author kalmankostenszky
 */
public class DateParam {

	private static final Logger LOG = Logger.getLogger(DateParam.class);

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar date;

	public DateParam(String date) throws WebApplicationException {
		if (date == null || date.equalsIgnoreCase("")) {
			this.date = null;
			LOG.info("null date created");
		} else {
			try {
				this.date = Calendar.getInstance();
				this.date.setTime((df.parse(date)));
			} catch (Exception e) {
				LOG.error(e);
				throw new WebApplicationException(e, Response.status(400).entity(new RestError(-99, "date format allowed: yyyy-MM-dd")).build());
			}
		}
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}
}
