package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity
public class InventoryRecord {
    @Id @GeneratedValue
    private long id;

    @Column(name = "quantity", nullable = false)
    private double quantityInGrams;

    @OneToOne
    private User owner;

    @OneToOne
    private FoodItem item;

    public InventoryRecord(double quantityInGrams, User owner, FoodItem item) {
        this.quantityInGrams = quantityInGrams;
        this.owner = owner;
        this.item = item;
    }

    public InventoryRecord() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getQuantityInGrams() {
        return quantityInGrams;
    }

    public void setQuantityInGrams(double quantityInGrams) {
        this.quantityInGrams = quantityInGrams;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public FoodItem getItem() {
        return item;
    }

    public void setItem(FoodItem item) {
        this.item = item;
    }
}
