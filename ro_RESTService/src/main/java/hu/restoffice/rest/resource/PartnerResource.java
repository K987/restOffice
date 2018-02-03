/**
 *
 */
package hu.restoffice.rest.resource;

import hu.rest.hal.representation.Representation;
import hu.restoffice.rest.model.PartnerStub;

/**
 * @author kalmankostenszky
 *
 */
public class PartnerResource extends Resource<PartnerStub> {

    /**
     * @param stub
     */
    public PartnerResource(final PartnerStub stub) {
        super(stub);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see hu.restoffice.rest.resource.Resource#asRepresentation()
     */
    @Override
    public Representation asRepresentation() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * hu.restoffice.rest.resource.Resource#toResource(hu.rest.hal.representation.
     * Representation)
     */
    @Override
    public void toResource(final Representation halRepresentation) {
        // TODO Auto-generated method stub

    }

}
