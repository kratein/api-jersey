package com.example.entities;

import com.example.entities.Entities;

public class Photo extends Entities {
    private String title;
    private String path;
    private String description;
    private String id_activity;

    @Override
    public String toString() {
        return "{" +
            " id'=" + getId() + "'" +
            " title='" + getTitle() + "'" +
            ", path='" + getPath() + "'" +
            ", description='" + getDescription() + "'" +
            ", id_activity='" + getId_activity() + "'" +
            "}";
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

    public String getId_activity() {
        return this.id_activity;
    }

    public void setId_activity(String id_activity) {
        this.id_activity = id_activity;
    }

    public Photo(int id, String title, String path, String description, String id_activity) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.description = description;
        this.id_activity = id_activity;
    }

    public Photo() {
    }


} 