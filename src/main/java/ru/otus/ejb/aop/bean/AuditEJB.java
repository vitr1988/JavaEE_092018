package ru.otus.ejb.aop.bean;

import ru.otus.ejb.aop.interceptor.AuditInterceptor;

import javax.ejb.Stateless;

@Stateless
@AuditInterceptor.Auditable
public class AuditEJB {

    public String bizMethod(){
        //any calls to this method will be intercepted by AuditInterceptor.audit()
        return "Your ads could be here";
    }
}
