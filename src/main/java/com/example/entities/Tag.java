package com.example.entities;

import java.io.Serializable;

public class Tag extends Entities implements Serializable {
    private static final long serialVersionUID = 1L;
    private String label;

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag() {
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " label='" + getLabel() + "'" +
            "}";
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}