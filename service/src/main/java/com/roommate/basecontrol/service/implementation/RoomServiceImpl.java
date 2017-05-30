package com.roommate.basecontrol.service.implementation;


import com.roommate.basecontrol.service.api.RoomService;
import com.roommate.basecontrol.repository.dao.api.RoomDAO;
import com.roommate.basecontrol.repository.entities.Room;
import com.roommate.basecontrol.utils.exceptions.DAOException;
import com.roommate.basecontrol.utils.exceptions.EntityAlreadyExistsException;
import com.roommate.basecontrol.utils.exceptions.RoomNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artem Karnov @date 17.11.2016.
 *         artem.karnov@t-systems.com
 **/
@Service("roomService")
public class RoomServiceImpl implements RoomService {
    private static Logger logger = LogManager.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomDAO roomDAO;


    @Override
    @Transactional
    public void createEntity(Room room) throws DAOException {
        if (!isRoomExists(room)) {
            logger.info("Room " + room + "was successfully created");
            roomDAO.create(room);
        } else {
            throw new EntityAlreadyExistsException("Room " + room.getTitle() + " already exists");
        }
    }

    /**
     * Get room entity by id
     *
     * @param id id for getting
     * @return room with adjusted id
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public Room getEntityById(Integer id) throws DAOException {
        return roomDAO.read(id);
    }

    /**
     * Update room entity in base
     *
     * @param entity entity for updating
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void updateEntity(Room entity) throws DAOException {
        roomDAO.update(entity);
    }

    /**
     * Delete room entity from base
     *
     * @param entity entity for deleting
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public void deleteEntity(Room entity) throws DAOException {
        roomDAO.delete(entity);
    }

    /**
     * Getting all room com.roommate.basecontrol.repository.entities from base
     *
     * @return list of all rooms
     * @throws DAOException if connect with DAO goes wrong
     */
    @Override
    @Transactional
    public List<Room> getAll() throws DAOException {
        return roomDAO.getAll();
    }

    /**
     * Getting room entity by email
     *
     * @param title entity for getting
     * @return room with adjusted email
     * @throws RoomNotFoundException if room not found
     */
    @Override
    @Transactional
    public Room getRoomByTitle(String title) throws RoomNotFoundException {
        return roomDAO.getRoomByTitle(title);
    }

    /**
     * Checking room existing in base
     *
     * @param room entity for checking
     * @return true - if room exists, false if doesn't
     */
    public boolean isRoomExists(Room room) {
        try {
            return getRoomByTitle(room.getTitle()) != null ? true : false;
        } catch (RoomNotFoundException ex) {
            logger.warn("Room " + room + "isn't exist");
            return false;
        }
    }

}
