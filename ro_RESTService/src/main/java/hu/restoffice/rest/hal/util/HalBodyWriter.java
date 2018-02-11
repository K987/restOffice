/**
 *
 */
package hu.restoffice.rest.hal.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import hu.rest.hal.exception.ResourceProcessingException;
import hu.rest.hal.exception.ResourceWriteException;
import hu.rest.hal.jaxrs.HalMediaType;
import hu.rest.hal.jaxrs.Representable;

/**
 * @author kalmankostenszky
 *
 */
@SuppressWarnings("rawtypes")
@Provider
@Produces({ HalMediaType.MEDIATYPE_HAL_JSON, HalMediaType.MEDIATYPE_HAL_XML })
public class HalBodyWriter implements MessageBodyWriter<Representable> {

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
                && Representable.class.isAssignableFrom(type))
            return true;
        else
            return false;
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
        if (mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_JSON_TYPE)) {
            try {
                Converter.JSON.writeTo(t.asRepresentation(), writer);
            } catch (ResourceWriteException | ResourceProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new IOException();
            }
        } else if (mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_XML_TYPE)) {
            try {
                Converter.XML.writeTo(t.asRepresentation(), writer);
            } catch (ResourceWriteException | ResourceProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new IOException();
            }
        } else {
            throw new WebApplicationException("unsupported media type");
        }
    }

}
