package ru.otus.webservice.rest.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.ws.rs.QueryParam;

@Data
@FieldNameConstants
public class User {

    @QueryParam(FIELD_ID)
    private long id;

    @QueryParam(FIELD_LOGIN)
    private String login;

    @QueryParam(FIELD_FIO)
    private String fio;

    @QueryParam(FIELD_AGE)
    private int age;
}
