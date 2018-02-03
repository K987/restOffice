/**
 *
 */
package hu.restoffice.rest.hal.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import hu.rest.hal.exception.ResourceReadException;
import hu.rest.hal.representation.Representation;

/**
 * @author kalmankostenszky
 *
 */
@Provider
@Consumes({ HalMediaType.HAL_JSON, HalMediaType.HAL_XML })
public class HalBodyReader implements MessageBodyReader<Representation> {

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.MessageBodyReader#isReadable(java.lang.Class,
     * java.lang.reflect.Type, java.lang.annotation.Annotation[],
     * javax.ws.rs.core.MediaType)
     */
    @Override
    public boolean isReadable(final Class<?> type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        if ((mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_JSON)
                || mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_XML))
                && Representation.class.isAssignableFrom(type))
            return true;
        else
            return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.MessageBodyReader#readFrom(java.lang.Class,
     * java.lang.reflect.Type, java.lang.annotation.Annotation[],
     * javax.ws.rs.core.MediaType, javax.ws.rs.core.MultivaluedMap,
     * java.io.InputStream)
     */
    @Override
    public Representation readFrom(final Class<Representation> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, String> httpHeaders,
            final InputStream entityStream) throws IOException, WebApplicationException {
        InputStreamReader reader = new InputStreamReader(entityStream, "UTF-8");
        if (mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_JSON)) {
            try {
                return Converter.JSON.readFrom(reader);
            } catch (ResourceReadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new IOException();
            }
        } else if (mediaType.isCompatible(HalMediaType.MEDIATYPE_HAL_XML)) {
            try {
                return Converter.XML.readFrom(reader);
            } catch (ResourceReadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new IOException();
            }
        } else {
            throw new WebApplicationException("unsupported media type");
        }
    }
}
