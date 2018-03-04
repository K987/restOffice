package hu.restoffice.persistence.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public abstract class PersistenceService<T> {

    @PersistenceContext(unitName = "ro-persistence-unit")
    protected EntityManager manager;

    private Class<T> clazz;

    public T findById(final Long id) {
        return manager.find(clazz, id);
    }
}
