package com.restaurantes.tamara.proyectoasee;

import java.util.Comparator;

/**
 * Created by Tamara on 01/11/2015.
 */
public class Review {
    private String id;
    private String restaurant;
    private String name;
    private String review;
    private float rates;

    public Review(){};
    public Review(String id,String restaurant, String name, String review, float rates){
        this.id = id;
        this.restaurant = restaurant;
        this.name = name;
        this.review = review;
        this.rates = rates;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurant() { return restaurant; }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRates() { return rates; }

    public void setRates(float rates) {
        this.rates = rates;
    }

}
