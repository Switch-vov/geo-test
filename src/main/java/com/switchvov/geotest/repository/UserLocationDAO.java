package com.switchvov.geotest.repository;

import com.switchvov.geotest.common.mongodb.dao.MongoDBBaseDAO;
import com.switchvov.geotest.model.UserLocation;

import java.util.Collection;

/**
 * @Author Switch
 * @Date 2018/1/13
 */
public interface UserLocationDAO extends MongoDBBaseDAO<UserLocation> {
    void batchSave(Collection<? extends UserLocation> batch);
}
