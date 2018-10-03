package ru.otus.json.model;

import lombok.Data;

@Data
public class User {

    private String login;
    private char[] password;

}
