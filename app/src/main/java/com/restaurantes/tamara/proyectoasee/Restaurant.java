package com.restaurantes.tamara.proyectoasee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tamara on 25/10/2015.
 */
public class Restaurant {
    private String ID;
    private String name;
    private String address;
    private Integer CP;
    private Integer phone;
    private String description;
    private String email;
    private String web;
    private Integer category;
    private Integer forks;
    private Integer capacity;
    private Integer latitude;
    private Integer longitude;
    private float rate;

    public Restaurant(){}

    public Restaurant(String ID, String name, String address, Integer CP, Integer phone,String description, String email, String web, Integer category,
               Integer forks, Integer capacity) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.CP = CP;
        this.phone = phone;
        this.description = description;
        this.email = email;
        this.web = web;
        this.category = category;
        this.forks = forks;
        this.capacity = capacity;
//        this.latitude = latitude;         // No los he necesitado
//        this.longitude = longitude;
    }

    public String getID(){
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getCP() {
        return CP;
    }

    public void setCP(Integer CP) {
        this.CP = CP;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
