package hu.restoffice.persistence.service;

import javax.ejb.Local;

/**
 *
 */
@Local
public interface PersistenceServiceLocal<T> {

    T findById(Long id);

}
