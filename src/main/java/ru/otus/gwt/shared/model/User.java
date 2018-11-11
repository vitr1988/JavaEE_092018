package ru.otus.gwt.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User implements IsSerializable {

    @NotNull
    @Size(min = 4, message = "Login must contain at least 4 characters.")
    private String login;

    @NotNull
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = String.valueOf(password);
    }
}
