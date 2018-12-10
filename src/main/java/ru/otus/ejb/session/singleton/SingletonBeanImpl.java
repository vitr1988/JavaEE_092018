package ru.otus.ejb.session.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
//@DependsOn()
@ConcurrencyManagement(value = ConcurrencyManagementType.BEAN)
public class SingletonBeanImpl implements SingletonBean {

    private Map<Long, String> map;

//    @Resource
//    TimerService timerService;

    @PostConstruct
    private void init(){
        map = new ConcurrentHashMap<>();
//        timerService.createTimer(0,1000, "Every second timer with no delay");
    }

//    @Lock(LockType.WRITE)
    @Override
    public void put(Long key, String name){
        map.put(key, name);
    }

//    @Lock(LockType.READ)
    @Override
    public String get(Long key) {
        return map.get(key);
    }

    @Schedule(hour = "*", minute = "*", second = "*/5", info = "Every 5 seconds timer")
    public void automaticallyScheduled(Timer timer) {
        System.out.println("Hello " + map.values().stream().findFirst().orElseGet(() -> "NoBody"));
    }

    @PreDestroy
    public void destroy() {
        map.clear();
    }

    @AroundTimeout
    public Object timeoutInterceptorMethod(InvocationContext ctx) throws Exception {
        return ctx.proceed();
    }
}
