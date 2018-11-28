package ru.otus.webservice.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {
    private int id;
    private String name;
    private String description;
}
