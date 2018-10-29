package ru.otus.jsp.model;

import lombok.Data;

@Data
public class Employee {

    private String name;
    private int id;
    private String role;
    private Address address;
}
