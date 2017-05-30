package com.roommate.basecontrol.model.dto;

import com.roommate.basecontrol.repository.entities.User;

import java.util.List;

/**
 * @author Artem Karnov @date 30.01.17.
 *         artem.karnov@t-systems.com
 */
public class GroupDTO {
    private String title;
    private List<User> users;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
