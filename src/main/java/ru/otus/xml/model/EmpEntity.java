package ru.otus.xml.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import ru.otus.xml.adapter.DeptAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

@Data
@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmpEntity implements Serializable {

    @XmlAttribute(required = true)
    private long empno;

    @XmlElement(required = true)
    private String ename;

    @XmlElement(required = true)
    private String job;

    @XmlElement
    private Long mgr;

    @XmlTransient
    private Date hiredate;

    @XmlAttribute
    private Long sal;

    @XmlTransient
    private Long comm;

    @XmlElement(name="department")
//    @XmlJavaTypeAdapter(DeptAdapter.class)
    private DeptEntity deptNo;
}
