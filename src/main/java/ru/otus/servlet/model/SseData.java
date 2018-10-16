package ru.otus.servlet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SseData {

    private String data;
    private boolean fresh;
}
