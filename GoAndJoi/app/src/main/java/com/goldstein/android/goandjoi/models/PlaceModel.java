package com.goldstein.android.goandjoi.models;

import java.util.List;

/**
 * Created by Goldstein on 22/06/2016.
 */
public class PlaceModel {

    int id;
    String name;
    String description;
    private String duration;
    private String location;
    public PlaceModel(String name, String description, String location, String duration)
    {
        this.name = name;
        this.description = description;
        this.location = location;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLocation(String location) {
        this.location = location;
    }

        public String getDuration() {
        return duration;
    }
    public String getLocation() {
        return location;
    }

}
