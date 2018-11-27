package ru.otus.webservice.rest.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.ws.rs.QueryParam;

@Data
@FieldNameConstants
public class User {

    @ApiParam(value = FIELD_ID)
    @QueryParam(FIELD_ID)
    private long id;

    @ApiParam(value = FIELD_LOGIN, required = true)
    @QueryParam(FIELD_LOGIN)
    private String login;

    @ApiParam(value = FIELD_FIO, required = true)
    @QueryParam(FIELD_FIO)
    private String fio;

    @ApiParam(value = FIELD_AGE, required = true)
    @QueryParam(FIELD_AGE)
    private int age;
}
