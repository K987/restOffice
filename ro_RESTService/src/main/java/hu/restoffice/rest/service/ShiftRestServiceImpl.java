/**
 * 
 */
package hu.restoffice.rest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import hu.restoffice.rest.exception.RestError;
import hun.restoffice.ejbservice.domain.CalendarScheduleStub;
import hun.restoffice.ejbservice.exception.AdaptorException;
import hun.restoffice.ejbservice.facade.ShiftFacadeLocal;

/**
 * An implementation for shift services
 *
 * @author kalmankostenszky
 */
@Stateless
public class ShiftRestServiceImpl implements ShiftRestService {

	@EJB
	ShiftFacadeLocal facade;

	private static final Logger LOG = Logger.getLogger(ShiftRestServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hu.restoffice.restService.service.EmployeeRestService#getCalendarSchedule(java.lang.String,
	 * hu.restoffice.restService.converter.RESTDate, hu.restoffice.restService.converter.RESTDate)
	 */
	@Override
	public List<CalendarScheduleStub> getCalendarSchedule(UriInfo info) throws AdaptorException, WebApplicationException {
		LOG.info("get calendar schedule invoked w/ param: " + info);

		MultivaluedMap<String, String> queryParams = info.getQueryParameters();

		String paramFrom = queryParams.getFirst("from");
		String paramTo = queryParams.getFirst("to");

		Calendar from;
		Calendar to;

		try {
			from = convertToCalendar(paramFrom);
			to = convertToCalendar(paramTo);
		} catch (ParseException e) {
			throw new WebApplicationException(e, Response.status(400).entity(new RestError(-100, "Allowed date format is yyyy-MM-dd")).build());
		}
		LOG.info("invoking get schedule");
		
		List<CalendarScheduleStub> rtrn = this.facade.getCalendarSchedule(from, to);
		Collections.sort(rtrn);
		return rtrn;
	}

	/**
	 * @param paramFrom
	 * @return
	 * @throws ParseException
	 */
	private Calendar convertToCalendar(String param) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (param == null || param.equalsIgnoreCase(""))
			return Calendar.getInstance();
		else {
			Calendar rtrn = Calendar.getInstance();
			rtrn.setTime(df.parse(param));
			return rtrn;
		}
	}

}
