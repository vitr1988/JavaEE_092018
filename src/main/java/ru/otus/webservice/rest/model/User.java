package ru.otus.webservice.rest.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.ws.rs.QueryParam;

@Data
@FieldNameConstants(prefix = "")
public class User {

    @QueryParam(ID)
    private long id;

    @QueryParam(LOGIN)
    private String login;

    @QueryParam(FIO)
    private String fio;

    @QueryParam(AGE)
    private int age;
}
