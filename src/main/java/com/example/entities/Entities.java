package com.example.entities;

import java.io.Serializable;

public abstract class Entities implements Serializable {
    private static final long serialVersionUID = 1L;
	protected int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Entities)) {
            return false;
        }
        Entities entities = (Entities) o;
        return id == entities.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}