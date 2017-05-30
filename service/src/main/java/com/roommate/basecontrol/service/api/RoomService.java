package com.roommate.basecontrol.service.api;


import com.roommate.basecontrol.repository.entities.Room;
import com.roommate.basecontrol.utils.exceptions.RoomNotFoundException;

/**
 * @author Artem Karnov @date 17.11.2016.
 *         artem.karnov@t-systems.com
 **/
public interface RoomService extends GenericService<Room, Integer> {
    public com.roommate.basecontrol.repository.entities.Room getRoomByTitle(String title) throws RoomNotFoundException;
}
