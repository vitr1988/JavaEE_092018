package ru.otus.security.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ROLES")
public class Role {
    @Id
    public String rolename;

    @JoinColumn(name = "USERNAME")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
