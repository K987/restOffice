package hu.restoffice.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import hu.restoffice.rest.hal.provider.BaseUriProvider;
import hu.restoffice.rest.hal.provider.RepresentableBodyWriter;
import hu.restoffice.rest.hal.provider.RepresentationBodyWriter;
import hu.restoffice.rest.service.PartnerServiceImpl;

public class PartnerServiceImplTest extends JerseyTest{

	 
    @Override
    protected Application configure() {
        return new ResourceConfig(PartnerServiceImpl.class).register(RepresentationBodyWriter.class).register(BaseUriProvider.class);
    }
    
    @Test
	public void readTest() throws Exception {
		target("/partner/10").request().get();
	}
}
