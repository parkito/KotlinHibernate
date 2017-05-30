package com.roommate.basecontrol.repository.dao.api;


import com.roommate.basecontrol.utils.exceptions.DAOException;

import java.util.List;

/**
 * @author Artem Karnov 11.11.2016.
 *         artem.karnov@t-systems.com
 **/
public interface GenericDAO<E, K> {


    public void create(E entity) throws DAOException;

    public E read(K id) throws DAOException;

    public void update(E entity) throws DAOException;

    public void delete(E entity) throws DAOException;

    public List<E> getAll() throws DAOException;

}
