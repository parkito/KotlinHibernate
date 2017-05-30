package com.roommate.basecontrol.repository.dao.implementation;

import com.roommate.basecontrol.repository.dao.api.GenericDAO;
import com.roommate.basecontrol.utils.exceptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Artem Karnov @date 11.11.2016.
 *         artem.karnov@t-systems.com
 **/

public abstract class GenericDAOImpl<E, K> implements GenericDAO<E, K> {
    private static Logger logger = LogManager.getLogger(GenericDAOImpl.class);
    protected Class<E> daoType;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Parametrized constructor
     */
    public GenericDAOImpl() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * Creating entity in base
     *
     * @param entity entity for creating
     * @throws DAOException if connect with base goes wrong
     */
    @Override
    public void create(E entity) throws DAOException {
        try {
            entityManager.persist(entity);
        } catch (PersistenceException e) {
            throw new DAOException("Entity wasn't created: " + entity, e);
        }
        logger.info("Entity " + entity + "was successfully created");
    }

    /**
     * Reading entity from base
     *
     * @param id id for reading
     * @return E-entity if it exists
     * @throws DAOException if connect with base goes wrong
     */
    @Override
    public E read(K id) throws DAOException {
        try {
            return (E) this.entityManager.find(daoType, id);
        } catch (PersistenceException e) {
            throw new DAOException("Entity " + id + " wasn't found", e);
        }
    }

    /**
     * Refreshing entity in base
     *
     * @param entity entity for upgrade
     * @throws DAOException if connect with base goes wrong
     */
    @Override
    public void update(E entity) throws DAOException {
        try {
            entityManager.merge(entity);
        } catch (PersistenceException e) {
            throw new DAOException("Entity wasn't updated: " + entity, e);
        } catch (IllegalStateException e) {
            throw new DAOException("Entity wasn't updated: " + entity, e);
        }
        logger.info("Entity " + entity + "was successfully updated");

    }

    /**
     * Deleting entity from base
     *
     * @param entity entity for deleting
     * @throws DAOException if connect with base goes wrong
     */
    @Override
    public void delete(E entity) throws DAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        } catch (PersistenceException e) {
            throw new DAOException("Entity wasn't deleted: " + entity, e);
        }
        logger.info("Entity " + entity + "was successfully deleted");
    }

    /**
     * Getting all same-type com.roommate.basecontrol.repository.entities from base
     *
     * @return list of all com.roommate.basecontrol.repository.entities
     * @throws DAOException if connect with base goes wrong
     */
    @Override
    public List<E> getAll() throws DAOException {
        try {
            return entityManager.createNamedQuery(daoType.getSimpleName() + ".getAll", daoType).getResultList();
        } catch (PersistenceException ex) {
            throw new DAOException("Unable to get all com.roommate.basecontrol.repository.entities of class " + daoType.getSimpleName(), ex);
        }
    }
}
