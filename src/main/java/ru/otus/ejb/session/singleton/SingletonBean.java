package ru.otus.ejb.session.singleton;

import javax.ejb.Remote;

@Remote
public interface SingletonBean {
    void put(Long key, String name);
    String get(Long key);
}
