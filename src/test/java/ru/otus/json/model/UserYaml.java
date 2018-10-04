package ru.otus.json.model;

import lombok.Data;

import java.util.Map;

@Data
public class UserYaml {
    private String name;
    private int age;
    private Map<String, String> address;
    private String[] roles;
}
