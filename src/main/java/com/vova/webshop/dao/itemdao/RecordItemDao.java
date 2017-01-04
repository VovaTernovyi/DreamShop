package com.vova.webshop.dao.itemdao;

import java.util.List;

public interface RecordItemDao<T> {

    T findById(int id);

    void save(T t);

    List<T> findAllInstances();
}