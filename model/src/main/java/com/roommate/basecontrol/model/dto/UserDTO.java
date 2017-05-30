package com.roommate.basecontrol.model.dto;

import com.roommate.basecontrol.repository.entities.Group;

import java.io.Serializable;
import java.util.List;

/**
 * @author Artem Karnov @date 07.01.17.
 *         artem.karnov@t-systems.com
 **/
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -7788619177798333712L;

    private String name;
    private String secondName;
    private String email;
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
