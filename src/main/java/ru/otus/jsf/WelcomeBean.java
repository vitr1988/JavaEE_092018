package ru.otus.jsf;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Data
@ManagedBean(name = "welcome", eager = true)
////@Model
//@Named("welcome")
public class WelcomeBean {

    private String message = "Hello World!";

    public String print() {
        System.out.println(message);
        return message;
    }
}
