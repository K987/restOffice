/**
 *
 */
package hu.restoffice.persistence.service;

import java.util.List;

import javax.ejb.Local;

import hu.restoffice.persistence.domain.Partner;
import hun.restoffice.persistence.exception.PersistenceServiceException;

/**
 * Partner persistence interface
 *
 * @author kalmankostenszky
 */

@Local
public interface PartnerServiceLocal {

    /**
     * Select partner by name
     *
     * @param partnerName
     * @return first partner who matches the search criteria
     * @throws PersistenceServiceException
     */
    public Partner read(String partnerName) throws PersistenceServiceException;

    /**
     * Select all partners
     *
     * @param technical
     *            true: to read technical only, false: to read non-technical only, null: to read all
     * @return
     * @throws PersistenceServiceException
     */
    public List<Partner> readAll(Boolean technical) throws PersistenceServiceException;

    /**
     * Delete all partner enitites w/ no relation to income or expense entitites
     *
     * @return
     * @throws PersistenceServiceException
     */
    public List<Partner> deleteUnused() throws PersistenceServiceException;

    /**
     * add partner entity if not exists by name already
     *
     * @param partner
     * @return persisted partner
     * @throws PersistenceServiceException
     */
    public Partner create(Partner partner) throws PersistenceServiceException;

    /**
     * update partner entity if esxits by name already
     *
     * @param partner
     * @return updated partner
     * @throws PersistenceServiceException
     */
    public Partner update(Partner partner) throws PersistenceServiceException;

    /**
     * @param id
     * @return
     */
    public Partner readById(Integer id) throws PersistenceServiceException;

}
