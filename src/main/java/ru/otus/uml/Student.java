package ru.otus.uml;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Student extends Human {

    private int courseYear;
    private String specialization;
    private BigDecimal compensation;
    private List<Subject> favorites;

}
