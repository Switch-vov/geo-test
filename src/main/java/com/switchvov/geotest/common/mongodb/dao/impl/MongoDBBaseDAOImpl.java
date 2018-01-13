package com.switchvov.geotest.common.mongodb.dao.impl;

import com.switchvov.geotest.common.mongodb.dao.MongoDBBaseDAO;
import com.switchvov.geotest.common.util.Pager;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class MongoDBBaseDAOImpl<T> implements MongoDBBaseDAO<T> {
    private Class<T> clazz;

    protected MongoTemplate mongoTemplate;

    public MongoDBBaseDAOImpl() {
        Class c = this.getClass();
        Type type = c.getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) type;
        Type[] actualTypeArguments = pType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    @Override
    public List<T> find(Query query) {
        return mongoTemplate.find(query, clazz);
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, clazz);
    }

    @Override
    public T update(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, clazz);
    }

    @Override
    public T upsert(Query query, Update update) {
        FindAndModifyOptions options = FindAndModifyOptions.options().upsert(true);
        return mongoTemplate.findAndModify(query, update, options, clazz);
    }

    @Override
    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, clazz);
    }

    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, clazz, collectionName);
    }

    @Override
    public Pager<T> findPage(Pager<T> page, Query query) {
        long count = this.count(query);
        page.setCount((int) count);
        query.skip(page.getOffset()).limit(page.getPageSize());
        List<T> data = this.find(query);
        page.setData(data);
        return page;
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, clazz);
    }

    protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);
}