/**
 *
 */
package hu.restoffice.rest.hal.util;

import javax.ws.rs.core.MediaType;

/**
 * @author kalmankostenszky
 *
 */
public class HalMediaType {

    static final String HAL_JSON = "application/hal+json";
    static final String HAL_XML = "application/hal+xml";
    static final MediaType MEDIATYPE_HAL_JSON = MediaType.valueOf(HAL_JSON);
    static final MediaType MEDIATYPE_HAL_XML = MediaType.valueOf(HAL_XML);
}
