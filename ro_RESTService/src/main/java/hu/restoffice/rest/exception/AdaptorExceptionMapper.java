/**
 * 
 */
package hu.restoffice.rest.exception;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.restoffice.rest.filter.CrossOriginRequestFilter;
import hun.restoffice.ejbservice.exception.AdaptorException;

/**
 * Exception mapper for AdaptorExceptions
 */
@Provider
public class AdaptorExceptionMapper implements ExceptionMapper<AdaptorException> {

	@Context
	private HttpHeaders headers;

	@Override
	public Response toResponse(final AdaptorException e) {
		return Response.status(e.getErrorCode().getHttpStatusCode())
				.entity(e.build()) //
				.header(CrossOriginRequestFilter.ALLOW_ORIGIN, "*") //
				.header(CrossOriginRequestFilter.ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, HEAD") //
				.header(CrossOriginRequestFilter.MAX_AGE, "1209600") //
				.header(CrossOriginRequestFilter.ALLOW_HEADERS, "x-requested-with, origin, content-type, accept, X-Codingpedia, authorization") //
				.header(CrossOriginRequestFilter.ALLOW_CREDENTIALS, "true") //
				.type(MediaType.APPLICATION_JSON).build();
	}

}