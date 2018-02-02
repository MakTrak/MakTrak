package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity(name="inventory")
public class InventoryRecord {
    @Id @GeneratedValue
    private long id;

    @Column(name = "quantity", nullable = false)
    private double quantity;

    @OneToOne
    private User owner;

    @OneToOne
    private FoodItem item;

    public InventoryRecord(double quantity, User owner, FoodItem item) {
        this.quantity = quantity;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
