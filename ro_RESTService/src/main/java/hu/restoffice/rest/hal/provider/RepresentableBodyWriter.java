/**
 *
 */
package hu.restoffice.rest.hal.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import hu.rest.hal.exception.ResourceProcessingException;
import hu.rest.hal.jaxrs.HalMediaType;
import hu.rest.hal.jaxrs.Representable;
import hu.rest.hal.representation.Representation;

/**
 * @author kalmankostenszky
 *
 */
@Provider
@Produces({ HalMediaType.MEDIATYPE_HAL_JSON, HalMediaType.MEDIATYPE_HAL_XML })
public class RepresentableBodyWriter implements MessageBodyWriter<Representable> {

    @Context
    UriInfo info;

    private static final Logger log = Logger.getLogger(RepresentableBodyWriter.class);

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.MessageBodyWriter#isWriteable(java.lang.Class,
     * java.lang.reflect.Type, java.lang.annotation.Annotation[],
     * javax.ws.rs.core.MediaType)
     */
    @Override
    public boolean isWriteable(final Class<?> type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        if ((mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_JSON_TYPE)
                || mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_XML_TYPE))
                && (Representable.class.isAssignableFrom(type))) {
            log.info("type is writeable as Representable entity body");
            return true;
        } else {
            log.info("type is not writeable as Representable entity body");
            return false;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.MessageBodyWriter#getSize(java.lang.Object,
     * java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[],
     * javax.ws.rs.core.MediaType)
     */
    @Override
    public long getSize(final Representable t, final Class<?> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType) {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.MessageBodyWriter#writeTo(java.lang.Object,
     * java.lang.Class, java.lang.reflect.Type, java.lang.annotation.Annotation[],
     * javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
     * java.io.OutputStream)
     */
    @Override
    public void writeTo(final Representable t, final Class<?> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> httpHeaders,
            final OutputStream entityStream) throws IOException, WebApplicationException {

        OutputStreamWriter writer = new OutputStreamWriter(entityStream, "UTF-8");
        try {
            log.info("writing Representable as " + mediaType + "...");
            if (mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_JSON_TYPE))

                Converter.JSON.writeTo(toRepresenation(t), writer);
            else if (mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_XML_TYPE))
                Converter.XML.writeTo(toRepresenation(t), writer);
            else {
                log.error("Representable media type is not supported");
                throw new WebApplicationException("unsupported mediatype for HAL resrouce " + mediaType);
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);

            e.printStackTrace(pw);
            log.error(sw.toString());
            log.error("can not write Representable :" + e);
            throw new WebApplicationException("Requested resource can not be created");
        }
    }

    /**
     * @param t
     * @return
     * @throws ResourceProcessingException
     */
    private Representation toRepresenation(final Representable t) throws ResourceProcessingException {
        Representation rtrn = t.asRepresentation();
        String baseUri = info.getBaseUri().toString();

        rtrn.getLinks().forEach(l -> l.setBaseUri(baseUri));
        rtrn.getEmbedded().values().forEach(c -> c.forEach(r -> r.getLinks().forEach(l -> l.setBaseUri(baseUri))));
        return rtrn;

    }

}
