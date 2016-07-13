package com.goldstein.android.goandjoi.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Goldstein on 20/06/2016.
 */
public class AtractionModel {

    int id;
    PlaceModel placeModel;
    List<String> imageList;

    int durationTimeType;
    int accessibilityType;
    int tripType;
    int tripLevelType;

    public AtractionModel(String name, String description, int durationTimeType,
                          int accessibilityType, int tripType, int tripLevelType, String location, String duration)
    {
        placeModel = new PlaceModel(name,description,location,duration);
        this.durationTimeType = durationTimeType;
        this.accessibilityType = accessibilityType;
        this.tripType = tripType;
        this.tripLevelType = tripLevelType;
        imageList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public PlaceModel getPlaceModel() {
        return placeModel;
    }

    public void setName(String name) {
         placeModel.name = name;
    }
    public void setDescription(String description) {
        placeModel.description = description;
    }

    public int getDurationTimeType() {
        return durationTimeType;
    }
    public int getAccessibilityType() {
        return accessibilityType;
    }
    public int getTripType() {
        return tripType;
    }
    public int getTripLevelType() {
        return tripLevelType;
    }

    public int setDurationTimeType() {
        return durationTimeType;
    }
    public int setAccessibilityType() {
        return accessibilityType;
    }
    public int setTripType() {
        return tripType;
    }
    public int setTripLevelType() {
        return tripLevelType;
    }

    public List<String> getImageList() {
        return imageList;
    }

}
