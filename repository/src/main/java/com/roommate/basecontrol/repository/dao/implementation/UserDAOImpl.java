package com.roommate.basecontrol.repository.dao.implementation;

import com.roommate.basecontrol.repository.dao.api.UserDAO;
import com.roommate.basecontrol.repository.entities.User;
import com.roommate.basecontrol.utils.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author Artem Karnov @date 11.11.2016.
 *         artem.karnov@t-systems.com
 **/
@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
    private static Logger logger = LogManager.getLogger(UserDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByEMAil(String eMail) throws UserNotFoundException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.email=:eMail").
                    setParameter("eMail", eMail);
            User user = (User) query.getSingleResult();
            logger.info("User with email = " + eMail + " was successfully read");
            return user;
        } catch (PersistenceException ex) {
            throw new UserNotFoundException("User with email {" + eMail + "} wasn't found!", ex);
        }
    }

}