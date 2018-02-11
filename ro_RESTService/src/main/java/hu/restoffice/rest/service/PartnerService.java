/**
 *
 */
package hu.restoffice.rest.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import hu.rest.hal.jaxrs.AsRelation;
import hu.rest.hal.jaxrs.HalMediaType;
import hu.restoffice.rest.model.PartnerStub;
import hun.restoffice.ejbservice.exception.AdaptorException;

@Path("/partner")
public interface PartnerService {

    @GET
    @Path("/{id}")
    @Produces({ HalMediaType.MEDIATYPE_HAL_JSON, HalMediaType.MEDIATYPE_HAL_XML })
    @AsRelation("self")
    Response read(@PathParam("id") Long id) throws AdaptorException;

    @GET
    @Path("/")
    @Produces({ HalMediaType.MEDIATYPE_HAL_JSON, HalMediaType.MEDIATYPE_HAL_XML })
    @AsRelation("getAll")
    Response readAll() throws AdaptorException;

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") Long id) throws AdaptorException;

    @PUT
    @Path("/")
    @Produces({ HalMediaType.MEDIATYPE_HAL_JSON, HalMediaType.MEDIATYPE_HAL_XML })
    Response create(PartnerStub partner) throws AdaptorException;


    @POST
    @Path("/{id}")
    @Produces({ HalMediaType.MEDIATYPE_HAL_JSON, HalMediaType.MEDIATYPE_HAL_XML })
    PartnerStub update(@PathParam("id") Long id, PartnerStub partner) throws AdaptorException;

}
