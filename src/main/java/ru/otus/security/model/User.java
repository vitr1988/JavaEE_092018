package ru.otus.security.model;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "USERS")
public class User implements Serializable {
    @Id
    private String username;

    @Column(columnDefinition = "VARCHAR(32)", length = 32)
    private String password;

    @JoinColumn(name = "ROLENAME")
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Role> roles;

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password).toUpperCase();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

