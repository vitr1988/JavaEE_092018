package ru.otus.xml.model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@Data
@ToString
@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeptEntity {

    @XmlAttribute(required = true)
    private long deptno;

    @XmlElement(required = true, name = "name")
    private String dname;

    @XmlElement(required = true)
    private String loc;
}