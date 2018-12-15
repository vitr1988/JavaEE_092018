package ru.otus.ejb.aop.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.InvocationContext;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@AuditInterceptor.Auditable
@Interceptor
@Priority(10)
public class AuditInterceptor {

    @AroundInvoke
    public Object audit(InvocationContext ictx) throws Exception {
        //before
        //logic goes here
        Object result = ictx.proceed();
        //after
        return result;
    }

    @InterceptorBinding
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Auditable {

    }
}
