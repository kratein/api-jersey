package com.example.entities;

import java.util.List;

import com.example.entities.Entities;

public class Activity extends Entities {
    private String label;
    private String description;
    private String web_site;
    private int min_older;
    private String street;
    private String zip_code;
    private List<Integer> id_tag;
    private String cover;
    private String slogan;
    private Double price;

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " label='" + getLabel() + "'" +
            ", description='" + getDescription() + "'" +
            ", web_site='" + getWeb_site() + "'" +
            ", min_older='" + getMin_older() + "'" +
            ", street='" + getStreet() + "'" +
            ", zip_code='" + getZip_code() + "'" +
            ", id_tag='" + getId_tag() + "'" +
            ", cover='" + getCover() + "'" +
            ", slogan='" + getSlogan() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeb_site() {
        return this.web_site;
    }

    public void setWeb_site(String web_site) {
        this.web_site = web_site;
    }

    public int getMin_older() {
        return this.min_older;
    }

    public void setMin_older(int min_older) {
        this.min_older = min_older;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip_code() {
        return this.zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public List<Integer> getId_tag() {
        return this.id_tag;
    }

    public void setId_tag(List<Integer> id_tag) {
        this.id_tag = id_tag;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Activity(int id, String label, String description, String web_site, int min_older, String street, String zip_code, List<Integer> id_tag, String cover, String slogan, Double price) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.web_site = web_site;
        this.min_older = min_older;
        this.street = street;
        this.zip_code = zip_code;
        this.id_tag = id_tag;
        this.cover = cover;
        this.slogan = slogan;
        this.price = price;
    }

    public Activity() {
    }
}