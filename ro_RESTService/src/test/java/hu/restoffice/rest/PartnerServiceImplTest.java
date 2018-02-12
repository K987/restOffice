package hu.restoffice.rest;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import hu.rest.hal.jaxrs.HalMediaType;
import hu.rest.hal.jaxrs.ResourceRegistry;
import hu.restoffice.rest.hal.provider.RepresentableBodyWriter;
import hu.restoffice.rest.model.PartnerStub;
import hu.restoffice.rest.service.PartnerService;
import hu.restoffice.rest.service.PartnerServiceImpl;

public class PartnerServiceImplTest extends JerseyTest{


    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        ResourceRegistry.addManager(PartnerStub.class, PartnerService.class);
        return new ResourceConfig(PartnerServiceImpl.class, RepresentableBodyWriter.class);
    }

    @Test
    public void readTest() throws Exception {
        Response r = target("/partner/10").request().accept(HalMediaType.MEDIATYPE_HAL_JSON).get();
        System.out.println(r);
    }
}
