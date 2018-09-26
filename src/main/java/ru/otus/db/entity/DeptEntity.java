package ru.otus.db.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DEPT")
public class DeptEntity {

    @Id
    @Column(name = "DEPTNO")
    private long deptno;

    @Basic
    @Column(name = "DNAME")
    private String dname;

    @Basic
    @Column(name = "LOC")
    private String loc;
}
