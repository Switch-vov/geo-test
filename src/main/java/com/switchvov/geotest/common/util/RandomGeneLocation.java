package com.switchvov.geotest.common.util;

import com.switchvov.geotest.model.UserLocation;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoLocation;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author Switch
 * @Date 2018/1/13
 */
public class RandomGeneLocation {

    private static final ThreadLocalRandom THREAD_LOCAL_RANDOM = ThreadLocalRandom.current();

    public static List<UserLocation> randomGeneUserLocations(int count) {
        return randomGeneUserLocations(count, "");
    }

    public static List<UserLocation> randomGeneUserLocations(int count, String prefix) {
        Objects.requireNonNull(prefix);
        return IntStream.range(0, count)
                .parallel()
                .mapToObj(index -> {
                    UserLocation userLocation = new UserLocation();
                    userLocation.setUsername(prefix + index);
                    Point location = new GeoJsonPoint(randomLng(), randomLat());
                    userLocation.setLocation(location);
                    Date now = new Date();
                    userLocation.setCreated(now);
                    userLocation.setUpdated(now);
                    return userLocation;
                }).collect(Collectors.toList());
    }

    public static List<GeoLocation<String>> randomGeneGeoLocations(int count) {
        return randomGeneGeoLocations(count, "");
    }

    public static List<GeoLocation<String>> randomGeneGeoLocations(int count, String prefix) {
        return IntStream.range(0, count)
                .parallel()
                .mapToObj(index -> new GeoLocation<>(prefix + index, new Point(randomLng(), randomRedisLat()))).collect(Collectors.toList());
    }

    private static double randomLng() {
        return THREAD_LOCAL_RANDOM.nextDouble(-180, 180);
    }

    private static double randomLat() {
        return THREAD_LOCAL_RANDOM.nextDouble(-90, 90);
    }

    private static double randomRedisLat() {
        return THREAD_LOCAL_RANDOM.nextDouble(-85.05112878, 85.05112878);
    }
}
