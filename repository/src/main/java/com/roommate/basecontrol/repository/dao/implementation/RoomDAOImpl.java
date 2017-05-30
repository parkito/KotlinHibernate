package com.roommate.basecontrol.repository.dao.implementation;

import com.roommate.basecontrol.repository.dao.api.RoomDAO;
import com.roommate.basecontrol.repository.entities.Room;
import com.roommate.basecontrol.utils.exceptions.RoomNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 * @author Artem Karnov @date 17.11.2016.
 *         artem.karnov@t-systems.com
 **/
@Repository("roomDAO")
public class RoomDAOImpl extends GenericDAOImpl<Room, Integer> implements RoomDAO {
    private static Logger logger = LogManager.getLogger(RoomDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @param title title of room for getting
     * @return room that was gotten
     */
    @Override
    public Room getRoomByTitle(String title) {
        try {
            Query query = entityManager.createQuery("select r from Room r where r.title=:title")
                    .setParameter("title", title);
            Room room = (Room) query.getSingleResult();
            logger.info("Room " + title + " was successfully read");
            return room;
        } catch (PersistenceException ex) {
            throw new RoomNotFoundException("Room " + title + " wasn't found!", ex);
        }
    }
}