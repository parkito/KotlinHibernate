package com.roommate.basecontrol.model.convertors.implementation;

import com.roommate.basecontrol.model.convertors.api.DTOtoEntityConverter;
import com.roommate.basecontrol.model.dto.UserDTO;
import com.roommate.basecontrol.repository.entities.User;
import com.roommate.basecontrol.service.api.UserService;
import com.roommate.basecontrol.utils.exceptions.EntityToDTOConvertException;
import com.roommate.basecontrol.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artem Karnov @date 25.01.2017.
 *         artem.karnov@t-systems.com
 **/
@Component
public class UserDTOtoUserEntity implements DTOtoEntityConverter<UserDTO, User> {
    // TODO: 14.04.2017 make it better

    @Autowired
    UserService userService;

    @Override
    public User convert(UserDTO dto) {
        User user = null;
        try {
            user = userService.getUserByEMAil(dto.getEmail());
        } catch (UserNotFoundException ex) {
            throw new EntityToDTOConvertException("User " + dto.getEmail() + " wasn't found", ex);
        }
        return user;
    }

    @Override
    public List<User> convertList(List<UserDTO> dtoList) {
        List<User> result = new ArrayList<>();
        for (UserDTO userDTO : dtoList) {
            result.add(convert(userDTO));
        }
        return result;
    }
}
