package ru.otus.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Data
@Entity
@Table(name = "EMP")
public class EmpEntity implements Serializable {

    @Id
    @Column(name = "EMPNO")
    private long empno;

    @Basic
    @Column(name = "ENAME")
    private String ename;

    @Basic
    @Column(name = "JOB")
    private String job;

    @Basic
    @Column(name = "MGR")
    private Long mgr;

    @Basic
    @Column(name = "HIREDATE")
    private Time hiredate;

    @Basic
    @Column(name = "SAL")
    private Long sal;

    @Basic
    @Column(name = "COMM")
    private Long comm;

    @ManyToOne
    @JoinColumn(name = "DEPTNO", referencedColumnName = "DEPTNO")
    private DeptEntity deptNo;

}
