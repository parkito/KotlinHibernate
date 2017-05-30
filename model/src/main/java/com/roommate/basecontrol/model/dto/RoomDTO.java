package com.roommate.basecontrol.model.dto;

import com.roommate.basecontrol.repository.entities.Group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Artem Karnov @date 30.01.17.
 *         artem.karnov@t-systems.com
 */

public class RoomDTO implements Serializable {

    private String title;
    private int maxMembers;
    private List<Group> groups = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
