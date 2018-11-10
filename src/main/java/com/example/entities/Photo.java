package com.example.entities;

import java.io.Serializable;

import com.example.entities.Entities;

public class Photo extends Entities implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String path;
    private String description;
    private int id_activity;

    @Override
    public String toString() {
        return "{" + " id'=" + getId() + "'" + " title='" + getTitle() + "'" + ", path='" + getPath() + "'"
                + ", description='" + getDescription() + "'" + ", id_activity='" + getId_activity() + "'" + "}";
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_activity() {
        return this.id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    public Photo(int id, String title, String path, String description, int id_activity) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.description = description;
        this.id_activity = id_activity;
    }

    public Photo() {
    }

}