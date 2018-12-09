package ru.otus.ejb.session.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private static class ProxyInvocationHandler<S> implements InvocationHandler {

        private final S subject;

        public ProxyInvocationHandler(S object) {
            this.subject = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Class<?> daoClass = subject.getClass();
            System.out.println("Origin Class : " + daoClass.getName());
            // Actions before business logic
            Object result = method.invoke(subject, args);
            // Actions after business logic
            return result;
        }
    }

    public static <S> S create(S object) {
        Class<?> daoClass = object.getClass();
        return (S) Proxy.newProxyInstance(
                        ProxyFactory.class.getClassLoader(),
                        daoClass.getInterfaces(),
                        new ProxyInvocationHandler<S>(object));
    }
}
