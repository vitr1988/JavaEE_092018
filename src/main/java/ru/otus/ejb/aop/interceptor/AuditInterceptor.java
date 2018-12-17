package ru.otus.ejb.aop.interceptor;

import javax.annotation.Priority;
import javax.interceptor.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Random;

@AuditInterceptor.Auditable(flag = true)
@Interceptor
@Priority(10)
public class AuditInterceptor {

    @AroundInvoke
    public Object audit(InvocationContext ictx) throws Exception {
        Class<?> aClass = ictx.getTarget().getClass();
        if (aClass.isAnnotationPresent(Auditable.class)) {
            Auditable annotation = aClass.getAnnotation(Auditable.class);
            if (annotation.flag()) {

            }
        }
        //before
        //logic goes here
        Object result = new Random().nextBoolean() ? ictx.proceed() : null;
        //after
        return result;
    }

    @InterceptorBinding
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Auditable {
        boolean flag() default false;
    }
}
