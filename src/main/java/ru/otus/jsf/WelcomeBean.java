package ru.otus.jsf;

import lombok.Data;

import javax.faces.bean.ManagedBean;

@Data
@ManagedBean(name = "welcome", eager = true)
public class WelcomeBean {

    private String message = "Hello World!";
}
