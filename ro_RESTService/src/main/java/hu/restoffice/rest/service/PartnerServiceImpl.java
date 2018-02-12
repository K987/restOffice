/**
 *
 */
package hu.restoffice.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jdom2.Element;

import com.fasterxml.jackson.core.JsonGenerator;

import hu.rest.hal.exception.ResourceProcessingException;
import hu.rest.hal.jaxrs.JaxRsRepresentationBuilder;
import hu.rest.hal.jaxrs.Representable;
import hu.rest.hal.json.JsonContent;
import hu.rest.hal.representation.Representation;
import hu.rest.hal.xml.XmlContent;
import hu.restoffice.rest.model.PartnerContactStub;
import hu.restoffice.rest.model.PartnerStub;
import hun.restoffice.ejbservice.exception.AdaptorException;
import hun.restoffice.ejbservice.facade.PartnerFacadeLocal;

/**
 * Impelentation class of REST services on partner
 *
 * @author kalmankostenszky
 */
@Stateless
public class PartnerServiceImpl implements PartnerService {

    private static final Logger log = Logger.getLogger(PartnerServiceImpl.class);


    @EJB
    private PartnerFacadeLocal facade;

    /*
     * (non-Javadoc)
     *
     * @see hu.restoffice.rest.service.PartnerService#read(java.lang.Long)
     */
    @Override
    public Response read(final Long id) throws AdaptorException {
        log.info("read invoked");
        List<PartnerContactStub> details = new ArrayList<>();
        details.add(new PartnerContactStub(10l, "fő", "1234", "aki@gmail.com"));
        PartnerStub stub = new PartnerStub(10l, "raiker kereskedelmi kft", "raiker", "1021212-3213132-321313",
                "1024 Budapest Kis Ilona utca 3", "12235324-2313-21",
                details);

        return Response.ok(new Partner(stub)).build();
    }

    /*
     * (non-Javadoc)
     *
     * @see hu.restoffice.rest.service.PartnerService#readAll()
     */
    @Override
    public Response readAll() throws AdaptorException {
        return Response.ok("alma").build();
    }

    /*
     * (non-Javadoc)
     *
     * @see hu.restoffice.rest.service.PartnerService#delete(java.lang.Long)
     */
    @Override
    public Response delete(final Long id) throws AdaptorException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * hu.restoffice.rest.service.PartnerService#create(hu.restoffice.rest.model.
     * PartnerStub)
     */
    @Override
    public Response create(final PartnerStub partner) throws AdaptorException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see hu.restoffice.rest.service.PartnerService#update(java.lang.Long,
     * hu.restoffice.rest.model.PartnerStub)
     */
    @Override
    public PartnerStub update(final Long id, final PartnerStub partner) throws AdaptorException {
        // TODO Auto-generated method stub
        return null;
    }

    public class Partner implements Representable<PartnerStub> {

        PartnerStub stub;

        public Partner(final PartnerStub stub) {
            this.stub = stub;
        }
        /*
         * (non-Javadoc)
         *
         * @see hu.rest.hal.jaxrs.Representable#getRepresentable()
         */
        @Override
        public PartnerStub getRepresentable() {
            return stub;
        }

        /*
         * (non-Javadoc)
         *
         * @see hu.rest.hal.jaxrs.Representable#asRepresentable()
         */
        @Override
        public Representation asRepresentation() throws ResourceProcessingException {

            List<PartnerContact> details = new ArrayList<>();
            for (PartnerContactStub partnerContact : stub.getContacts()) {
                details.add(new PartnerContact(partnerContact));
            }

            JaxRsRepresentationBuilder b = JaxRsRepresentationBuilder.fromSelf(this);
            return b.property("legalName", stub.getLegalName())
                    .property("shortName", stub.getShortName()).property("bankAccountNo", stub.getBankAccountNo())
                    .property("address", stub.getAddress()).property("taxNo", stub.getTaxNo())
                    .property("contact details", details.toArray())
                    .link(b.getLinkBuilder("self", "next").build())
                    .build();
        }

        /*
         * (non-Javadoc)
         *
         * @see hu.rest.hal.jaxrs.Representable#getSelfId()
         */
        @Override
        public Object getSelfId() {
            return stub.getId();
        }

    }

    public class PartnerContact implements JsonContent, XmlContent {

        private PartnerContactStub stub;

        /**
         *
         */
        public PartnerContact(final PartnerContactStub stub) {
            this.stub = stub;
        }

        /*
         * (non-Javadoc)
         *
         * @see hu.rest.hal.xml.XmlContent#asContent(org.jdom2.Element)
         */
        @Override
        public void asContent(final Element parent) {
            parent.addContent(new Element("name").setText(stub.getName()));
            parent.addContent(new Element("email").setText(stub.getEmailAddress()));
            parent.addContent(new Element("phone").setText(stub.getPhoneNo()));
        }

        /*
         * (non-Javadoc)
         *
         * @see hu.rest.hal.json.JsonContent#writeObject(com.fasterxml.jackson.core.
         * JsonGenerator)
         */
        @Override
        public void writeObject(final JsonGenerator gen) throws IOException {
            gen.writeStartObject();
            gen.writeObjectField("name", stub.getName());
            gen.writeObjectField("email", stub.getEmailAddress());
            gen.writeObjectField("phone", stub.getPhoneNo());
            gen.writeEndObject();

        }

    }

}
