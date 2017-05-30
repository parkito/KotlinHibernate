package com.roommate.basecontrol.service.implementation;


import com.roommate.basecontrol.repository.dao.api.UserDAO;
import com.roommate.basecontrol.repository.entities.User;
import com.roommate.basecontrol.service.api.UserService;
import com.roommate.basecontrol.utils.exceptions.DAOException;
import com.roommate.basecontrol.utils.exceptions.EntityAlreadyExistsException;
import com.roommate.basecontrol.utils.exceptions.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artem Karnov @date 11.11.2016.
 *         artem.karnov@t-systems.com
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    /**
     * Creating contract user in base
     *
     * @param user entity for creating
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void createEntity(User user) throws DAOException {
        if (!isUserExists(user)) {
            logger.info("User " + user + "was successfully created");
            userDAO.create(user);
        } else {
            throw new EntityAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
    }

    /**
     * Get user entity by id
     *
     * @param id id for getting
     * @return user with adjusted id
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public User getEntityById(Integer id) throws DAOException {
        return userDAO.read(id);
    }

    /**
     * Update user entity in base
     *
     * @param entity entity for updating
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void updateEntity(User entity) throws DAOException {
        userDAO.update(entity);
    }

    /**
     * Delete user entity from base
     *
     * @param entity entity for deleting
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void deleteEntity(User entity) throws DAOException {
        userDAO.delete(entity);
    }

    /**
     * Getting all user com.roommate.basecontrol.repository.entities from base
     *
     * @return list of all users
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public List<User> getAll() throws DAOException {
        return userDAO.getAll();
    }

    /**
     * Getting user entity by email
     *
     * @param eMail entity for getting
     * @return user with adjusted email
     * @throws UserNotFoundException if user not found
     */
    @Override
    @Transactional
    public User getUserByEMAil(String eMail) throws UserNotFoundException {
        return userDAO.getUserByEMAil(eMail);
    }

    /**
     * Checking user existing in base
     *
     * @param user entity for checking
     * @return true - if user exists, false if doesn't
     */
    public boolean isUserExists(User user) {
        try {
            return getUserByEMAil(user.getEmail()) != null ? true : false;
        } catch (UserNotFoundException ex) {
            logger.warn("User " + user + "isn't exist");
            return false;
        }
    }
}