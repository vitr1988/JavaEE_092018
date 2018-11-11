package ru.otus.struts;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

public class ApplicationAction extends ActionSupport {
    private String name;
    private Date nowDate;

    @Override
    public void validate(){
        if (name == null || name.length() == 0)
            addActionError(getText("error.enter.message"));
    }

    @Override
    public String execute() throws Exception {
        nowDate = new Date();
        return ActionSupport.SUCCESS;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getNowDate() {
        return nowDate;
    }
}
