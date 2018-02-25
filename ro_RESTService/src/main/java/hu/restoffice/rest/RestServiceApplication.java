/**
 *
 */
package hu.restoffice.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import hu.rest.hal.jaxrs.ResourceRegistry;
import hu.restoffice.rest.model.PartnerStub;

/**
 * Entry class for rest services
 *
 * @author kalmankostenszky
 */
@ApplicationPath("/")
public class RestServiceApplication extends Application {

    private static final Logger log = Logger.getLogger(RestServiceApplication.class);

    public RestServiceApplication() {

        ResourceRegistry.addManager(PartnerStub.class, PartnerService.class);
    }
}
