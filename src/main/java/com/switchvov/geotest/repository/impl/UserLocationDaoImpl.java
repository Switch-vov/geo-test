package com.switchvov.geotest.repository.impl;

import com.switchvov.geotest.common.mongodb.dao.impl.MongoDBBaseDAOImpl;
import com.switchvov.geotest.model.UserLocation;
import com.switchvov.geotest.repository.UserLocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @Author Switch
 * @Date 2018/1/13
 */
@Repository
public class UserLocationDaoImpl extends MongoDBBaseDAOImpl<UserLocation> implements UserLocationDAO{

    @Override
    public void batchSave(Collection<? extends UserLocation> batch) {
        mongoTemplate.insert(batch, UserLocation.class);
    }

    @Override
    @Autowired
    @Qualifier("mongoTemplate")
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
