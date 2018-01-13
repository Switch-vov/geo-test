package com.switchvov.geotest.common.mongodb.dao;

import com.switchvov.geotest.common.util.Pager;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface MongoDBBaseDAO<T> {

    public List<T> find(Query query);

    public T findOne(Query query);

    public T update(Query query, Update update);

    public T upsert(Query query, Update update);

    public T save(T entity);

    public T findById(String id);

    public T findById(String id, String collectionName);

    public Pager<T> findPage(Pager<T> page, Query query);

    public long count(Query query);
}