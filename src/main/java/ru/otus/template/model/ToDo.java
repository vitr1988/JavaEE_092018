package ru.otus.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {

    private String user;
    private Date currentDate;
    private String text;
    private boolean done;

    public Function<Object, Object> checkAndPrint() {
        return obj -> done ? String.format("<small>Task has done successfully<small>", obj) : "";
    }
}
