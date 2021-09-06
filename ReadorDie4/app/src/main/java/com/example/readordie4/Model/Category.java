package com.example.readordie4.Model;

import com.google.firebase.database.PropertyName;

public class Category {
    String name, image, key;

    public Category() {
    }

    public Category(String name, String image, String key) {
        this.name = name;
        this.image = image;
        this.key = key;
    }

    @PropertyName("Name")
    public String getName() {
        return name;
    }

    @PropertyName("Name")
    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("Image")
    public String getImage() {
        return image;
    }

    @PropertyName("Image")
    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
