/**
 * 
 */
package hu.restoffice.restService.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hun.restoffice.ejbservice.domain.PartnerContactStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.ejbservice.exception.AdaptorException;

/**
 * REST services for partner management
 * 
 * @author kalmankostenszky
 */
@Path("/partner")
public interface PartnerRestService {

	/**
	 * Returns partner contact information
	 * 
	 * @param partnerName
	 *            the partners company name
	 * @return
	 * @throws AdaptorException
	 */
	@GET
	@Path("get/{partnerName}")
	@Produces("application/json")
	// Response getPartnerContact(@PathParam("partnerName") String partnerName) throws AdaptorException;
	PartnerContactStub getPartnerContact(@PathParam("partnerName") String partnerName) throws AdaptorException;

	/**
	 * Returns partner contact information too all partners
	 * 
	 * @return
	 * @throws AdaptorException
	 */
	@GET
	@Path("get/allContacts")
	@Produces("application/json")
	List<PartnerStub> getAllPartnerContacts() throws AdaptorException;

	/**
	 * Delete all partners who have no financial transactions
	 * 
	 * @return
	 * @throws AdaptorException
	 */
	@DELETE
	@Path("/delete")
	@Produces("application/json")
	List<PartnerStub> deleteUnusedPartners() throws AdaptorException;

	/**
	 * Add new partner
	 * 
	 * @param partner
	 *            partner data to add
	 * @return
	 * @throws AdaptorException
	 */
	@PUT
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	PartnerStub addPartner(PartnerStub partner) throws AdaptorException;

	/**
	 * Update existing partner
	 * 
	 * @param partner
	 *            partner data to update
	 * @return
	 * @throws AdaptorException
	 */
	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	PartnerStub updatePartner(PartnerStub partner) throws AdaptorException;

	/**
	 * 
	 * @param path 
	 * @return
	 * @throws AdaptorException
	 */
	@OPTIONS
	//@Path("{path:.*}")
	Response optionsAll(@PathParam("path") String path) throws AdaptorException;
}
