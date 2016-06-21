package com.goldstein.android.goandjoi.models;

import java.util.List;

/**
 * Created by Goldstein on 20/06/2016.
 */
public class AtractionModel {

    int id;
    String name;
    String description;
    List<String> imageList;

    public AtractionModel(String name, String description)
    {
        this.name = name;
        this.description = description;
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

    public List<String> getImageList() {
        return imageList;
    }
}
