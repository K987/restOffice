/**
 * 
 */
package hu.restoffice.rest.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import hun.restoffice.ejbservice.domain.CalendarScheduleStub;
import hun.restoffice.ejbservice.exception.AdaptorException;

/**
 *  REST services for shift management
 *
 * @author kalmankostenszky
 */
@Path("/shift")
public interface ShiftRestService {

	/**
	 * Get workdays schedule between two dates
	 * 	if now dates given: from: today - to: today + 14 days
	 * 
	 * @param uriInfo
	 * @return
	 * @throws AdaptorException
	 */
	@GET
	@Path("/getSchedule")
	@Produces(MediaType.APPLICATION_JSON)
	List<CalendarScheduleStub> getCalendarSchedule(@Context UriInfo uriInfo) throws AdaptorException;

}
