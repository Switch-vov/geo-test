package com.switchvov.geotest;

import com.switchvov.geotest.common.util.RandomGeneLocation;
import com.switchvov.geotest.model.UserLocation;
import com.switchvov.geotest.repository.UserLocationDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GisTestApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserLocationDAO userLocationDAO;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testRedis() {
        Set<ZSetOperations.TypedTuple<String>> set = redisTemplate.boundZSetOps("user:top").rangeWithScores(0, 3);
        Assert.assertNotNull(set);
        for (ZSetOperations.TypedTuple<String> tuple : set) {
            System.out.println(tuple.getScore() + " " + tuple.getValue());
        }
    }

    @Test
    public void testRandomGenGeoLocation() {
        for (int i = 0; i < 100; i++) {
            int count = 100000;
            List<GeoLocation<String>> geoLocations = RandomGeneLocation.randomGeneGeoLocations(count, "switch" + UUID.randomUUID().toString().substring(0, 4) + "-");
            redisTemplate.boundGeoOps("user:location").geoAdd(geoLocations);
            Assert.assertEquals(count, geoLocations.size());
            System.out.println(geoLocations);
        }
    }

    @Test
    public void testMongodb() {
        UserLocation userLocation = new UserLocation();
        userLocation.setUsername("switch");
        Point location = new GeoJsonPoint(113.9312989384, 22.5381246496);
        userLocation.setLocation(location);
        Date now = new Date();
        userLocation.setCreated(now);
        userLocation.setUpdated(now);
        userLocationDAO.save(userLocation);
        Assert.assertNotNull(userLocation.getId());
        System.out.println(userLocation);
    }

    @Test
    public void testRandomGenUserLocation() {
        for (int i = 0; i < 100; i++) {
            int count = 100000;
            List<UserLocation> userLocations = RandomGeneLocation.randomGeneUserLocations(count, "switch" + UUID.randomUUID().toString().substring(0, 4) + "-");
            userLocationDAO.batchSave(userLocations);
            Assert.assertEquals(count, userLocations.size());
            System.out.println(userLocations);
        }
    }
}
