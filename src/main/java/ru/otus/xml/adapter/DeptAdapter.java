package ru.otus.xml.adapter;

import ru.otus.xml.model.DeptEntity;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DeptAdapter extends XmlAdapter<String, DeptEntity> {
    public DeptEntity unmarshal(String v) throws Exception {
        DeptEntity deptEntity = new DeptEntity();
        deptEntity.setDname(v);
        return deptEntity;
    }
    public String marshal(DeptEntity v) throws Exception {
        return v == null ? null : v.toString();
    }
}
