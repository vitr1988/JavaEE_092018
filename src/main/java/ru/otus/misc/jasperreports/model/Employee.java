package ru.otus.misc.jasperreports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private BigDecimal EMPNO;
    private String ENAME;
    private String DNAME;
}
