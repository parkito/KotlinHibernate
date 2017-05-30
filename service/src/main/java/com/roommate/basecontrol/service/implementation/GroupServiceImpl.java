package com.roommate.basecontrol.service.implementation;


import com.roommate.basecontrol.service.api.GroupService;
import com.roommate.basecontrol.repository.dao.api.GroupDAO;
import com.roommate.basecontrol.repository.entities.Group;
import com.roommate.basecontrol.utils.exceptions.DAOException;
import com.roommate.basecontrol.utils.exceptions.EntityAlreadyExistsException;
import com.roommate.basecontrol.utils.exceptions.GroupNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artem Karnov @date 16.11.2016.
 *         artem.karnov@t-systems.com
 **/
@Service("groupService")
public class GroupServiceImpl implements GroupService {
    private static Logger logger = LogManager.getLogger(GroupServiceImpl.class);

    @Autowired
    private GroupDAO groupDAO;

    @Override
    @Transactional
    public void createEntity(Group grup) throws DAOException {
        if (!isGroupExists(grup)) {
            groupDAO.create(grup);
            logger.info("Group " + grup + "was successfully created");
        } else {
            throw new EntityAlreadyExistsException("Group " + grup.getTitle() + " already exists");
        }
    }

    /**
     * Get group entity by id
     *
     * @param id id for getting
     * @return group with adjusted id
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public Group getEntityById(Integer id) throws DAOException {
        return groupDAO.read(id);
    }

    /**
     * Update group entity in base
     *
     * @param entity entity for updating
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void updateEntity(Group entity) throws DAOException {
        groupDAO.update(entity);
    }

    /**
     * Delete group entity from base
     *
     * @param entity entity for deleting
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void deleteEntity(Group entity) throws DAOException {
        groupDAO.delete(entity);
    }

    /**
     * Getting all group com.roommate.basecontrol.repository.entities from base
     *
     * @return list of all groups
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public List<Group> getAll() throws DAOException {
        return groupDAO.getAll();
    }

    /**
     * Getting group entity by email
     *
     * @param title entity for getting
     * @return group with adjusted email
     * @throws UserNotFoundException if group not found
     */
    @Override
    @Transactional
    public Group getGroupByTitle(String title) throws GroupNotFoundException {
        return groupDAO.getGroupByTitle(title);
    }

    /**
     * Checking grup existing in base
     *
     * @param grup entity for checking
     * @return true - if grup exists, false if doesn't
     */
    public boolean isGroupExists(Group grup) {
        try {
            return getGroupByTitle(grup.getTitle()) != null ? true : false;
        } catch (GroupNotFoundException ex) {
            logger.warn("Group " + grup + "isn't exist");
            return false;
        }
    }

}
