/**
 *
 */
package hu.restoffice.rest.hal.util;

import hu.rest.hal.json.JsonConverter;
import hu.rest.hal.json.JsonConverterImpl;
import hu.rest.hal.xml.XmlConverter;
import hu.rest.hal.xml.XmlConverterImpl;

/**
 * @author kalmankostenszky
 *
 */
public class Converter {

    static JsonConverter JSON = new JsonConverterImpl();
    static XmlConverter XML = new XmlConverterImpl();
}
