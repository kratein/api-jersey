package com.example.entities;

import java.io.Serializable;
import java.sql.Date;

public class User extends Entities implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String firstname;
    private Date birthday;
    private String email;
    private String password;
    private String street; 
    private int zip_code;
    private String city;
    private int id_role;
    private String photo;

    public User(int id, String name, String firstname, Date birthday, String email, String password, String street, int zip_code, String city, int id_role, String photo) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
        this.street = street;
        this.zip_code = zip_code;
        this.city = city;
        this.id_role = id_role;
        this.photo = photo;
    }

    public User() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip_code() {
        return this.zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId_role() {
        return this.id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", street='" + getStreet() + "'" +
            ", zip_code='" + getZip_code() + "'" +
            ", city='" + getCity() + "'" +
            ", id_role='" + getId_role() + "'" +
            ", photo='" + getPhoto() + "'" +
            "}";
    }
    
}