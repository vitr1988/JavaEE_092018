package ru.otus.json.model.jsonb;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {

    private int id;
    @JsonbProperty("person-name")
    private String name;
    @JsonbProperty(nillable = true)
    private String email;
    @JsonbTransient
    private int age;
    @JsonbDateFormat("dd-MM-yyyy")
    private LocalDate registeredDate;
    private BigDecimal salary;

    public Person() {
        this(0, "", "", 0, LocalDate.now(), new BigDecimal(0));
    }
}
