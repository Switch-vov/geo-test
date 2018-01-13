package com.switchvov.geotest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author Switch
 * @Date 2018/1/13
 */
@Document(collection = "location")
public class UserLocation {
    @Id
    private String id;
    @Indexed(name = "idx_username")
    private String username;
    @GeoSpatialIndexed(name = "idx_location", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point location;
    @Indexed(name = "idx_created", direction = IndexDirection.DESCENDING)
    private Date created;
    @Indexed(name = "idx_updated", direction = IndexDirection.DESCENDING)
    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", location=" + location +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
