package hu.restoffice.rest.hal.provider;

import java.io.IOException;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.apache.log4j.Logger;

import hu.rest.hal.exception.ResourceProcessingException;
import hu.rest.hal.jaxrs.HalMediaType;
import hu.rest.hal.jaxrs.Representable;
import hu.rest.hal.link.HalLink;
import hu.rest.hal.representation.Representation;

@Provider
public class BaseUriProvider implements WriterInterceptor {

	private static final Logger log = Logger.getLogger(BaseUriProvider.class);

	@Context
	UriInfo info;

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		log.info("aroundWriteTo invoked");
		doApply(context);
		try {
			context.setEntity(createRepresentation(context.getEntity()));
		} catch (ResourceProcessingException e) {
			log.error(e.getMessage());
			throw new WebApplicationException(
					Response.serverError().entity("Entity not availble in the requested MediaType").build());
		}
		log.info("proceeding");
		context.proceed();
	}

	private Object createRepresentation(Object entity) throws ResourceProcessingException {

		Representation representation = (Representation) entity;
		for (HalLink link : representation.getLinks()) {
			link.setBaseUri(info.getBaseUri().toString());
		}
		return representation;
	}

	private void doApply(WriterInterceptorContext context) throws WebApplicationException, IOException {
		if (context.getEntity() instanceof Representation
				&& (context.getMediaType().isCompatible(HalMediaType.MEDIATYPE_HAL_JSON_TYPE)
						|| context.getMediaType().isCompatible(HalMediaType.MEDIATYPE_HAL_XML_TYPE))){
			return;
		}
		else
			context.proceed();

	}

}
