package com.roommate.basecontrol.service.api;


import com.roommate.basecontrol.repository.entities.Group;
import com.roommate.basecontrol.utils.exceptions.GroupNotFoundException;

/**
 * @author Artem Karnov @date 16.11.2016.
 *         artem.karnov@t-systems.com
 **/
public interface GroupService extends GenericService<Group, Integer> {
    public Group getGroupByTitle(String title) throws GroupNotFoundException;
}
