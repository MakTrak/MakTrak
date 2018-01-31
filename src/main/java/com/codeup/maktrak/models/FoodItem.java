package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity
@Table(name="items")
public class FoodItem {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double cal;

    @Column(nullable = false)
    private double fat;

    @Column(nullable = false)
    private double carb;

    @Column(nullable = false)
    private double prot;

    @Column(nullable = false)
    private double fiber;

    @Column(nullable = false, name="serving_size")
    private double servingSizeInGrams;

    public FoodItem(String name, double cal, double fat, double carb, double prot, double fiber, double servingSizeInGrams) {
        this.name = name;
        this.cal = cal;
        this.fat = fat;
        this.carb = carb;
        this.prot = prot;
        this.fiber = fiber;
        this.servingSizeInGrams = servingSizeInGrams;
    }

    public FoodItem() {
    }

    public void setNutritionToPerGram(double servingSizeInGrams) {
        this.cal /= servingSizeInGrams;
        this.fat /= servingSizeInGrams;
        this.carb /= servingSizeInGrams;
        this.prot /= servingSizeInGrams;
        this.fiber /= servingSizeInGrams;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCal() {
        return cal;
    }

    public void setCal(double cal) {
        this.cal = cal;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getProt() {
        return prot;
    }

    public void setProt(double prot) {
        this.prot = prot;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getServingSizeInGrams() {
        return servingSizeInGrams;
    }

    public void setServingSizeInGrams(double servingSizeInGrams) {
        this.servingSizeInGrams = servingSizeInGrams;
    }
}
