/**
 *
 */
package hu.restoffice.rest.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

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

    private static final Logger LOG = Logger.getLogger(PartnerServiceImpl.class);

    @EJB
    private PartnerFacadeLocal facade;

    /*
     * (non-Javadoc)
     * 
     * @see hu.restoffice.rest.service.PartnerService#read(java.lang.Long)
     */
    @Override
    public Response read(final Long id) throws AdaptorException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see hu.restoffice.rest.service.PartnerService#readAll()
     */
    @Override
    public Response readAll() throws AdaptorException {
        // TODO Auto-generated method stub
        return null;
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


}
