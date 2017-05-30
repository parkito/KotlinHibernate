package com.roommate.basecontrol.model.convertors.implementation;


import com.roommate.basecontrol.service.api.RoomService;
import com.roommate.basecontrol.model.convertors.api.DTOtoEntityConverter;
import com.roommate.basecontrol.model.dto.RoomDTO;
import com.roommate.basecontrol.repository.entities.Room;
import com.roommate.basecontrol.utils.exceptions.EntityToDTOConvertException;
import com.roommate.basecontrol.utils.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artem Karnov @date 25.01.2017.
 *         artem.karnov@t-systems.com
 **/
@Component
public class RoomDTOtoRoomEntity implements DTOtoEntityConverter<RoomDTO, Room> {
    @Autowired
    RoomService roomService;

    @Override
    public Room convert(RoomDTO dto) {
        Room room = null;
        try {
            room = roomService.getRoomByTitle(dto.getTitle());
        } catch (RoomNotFoundException ex) {
            throw new EntityToDTOConvertException("Room " + dto.getTitle() + " wasn't found", ex);
        }
        return room;
    }

    @Override
    public List<Room> convertList(List<RoomDTO> dtoList) {
        List<Room> result = new ArrayList<>();
        for (RoomDTO roomDTO : dtoList) {
            result.add(convert(roomDTO));
        }
        return result;
    }
}
