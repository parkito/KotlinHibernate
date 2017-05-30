package com.roommate.basecontrol.repository.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.roommate.basecontrol.utils.exceptions.DAOException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Artem Karnov 11.11.2016.
 *         artem.karnov@t-systems.com
 **/
@Entity
@Table(name = "User")
@NamedQuery(name = "User.getAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsers")
    private int idUsers;

    @Basic
    @Column(name = "Name", length = 45)
    private String name;

    @Basic
    @Column(name = "SecondName", length = 45)
    private String secondName;

    @Basic
    @Column(name = "Email", length = 45)
    private String email;

    @Basic
    @Column(name = "Password", length = 45)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_has_Grup", joinColumns = {
            @JoinColumn(name = "User_idUsers")},
            inverseJoinColumns = {@JoinColumn(name = "Grup_idGroups")})
    @JsonManagedReference
    private List<Group> groups;

    public User() {
    }

    public User(String name, String secondName, String email, String password) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
    }

    public void addGroup(Group grup) {
        if (!groups.contains(grup))
            groups.add(grup);
        else throw new DAOException(grup.getTitle() + " already in " + email);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public int getIdUsers() {
        return idUsers;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUsers != user.idUsers) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsers;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUsers=" + idUsers +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                groups +
                '}';
    }
}
