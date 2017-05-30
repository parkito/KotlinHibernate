package com.roommate.basecontrol.model.convertors.implementation;

import com.roommate.basecontrol.model.convertors.api.DTOtoEntityConverter;
import com.roommate.basecontrol.model.dto.GroupDTO;
import com.roommate.basecontrol.repository.entities.Group;
import com.roommate.basecontrol.service.api.GroupService;
import com.roommate.basecontrol.utils.exceptions.EntityToDTOConvertException;
import com.roommate.basecontrol.utils.exceptions.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artem Karnov @date 25.01.2017.
 *         artem.karnov@t-systems.com
 **/
@Component
public class GroupDTOtoGroupEntity implements DTOtoEntityConverter<GroupDTO, Group> {
    @Autowired
    GroupService groupService;

    @Override
    public Group convert(GroupDTO dto) {
        Group group = null;
        try {
            group = groupService.getGroupByTitle(dto.getTitle());
        } catch (GroupNotFoundException ex) {
            throw new EntityToDTOConvertException("Group " + dto.getTitle() + " wasn't found", ex);
        }
        return group;
    }

    @Override
    public List<Group> convertList(List<GroupDTO> dtoList) {
        List<Group> result = new ArrayList<>();
        for (GroupDTO groupEntity : dtoList) {
            result.add(convert(groupEntity));
        }
        return result;
    }
}
