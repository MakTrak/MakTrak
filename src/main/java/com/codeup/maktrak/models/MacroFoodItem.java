package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity(name="macro_item")
public class MacroFoodItem {
    @Id @GeneratedValue
    private long id;

    @Column(name = "quantity", nullable = false)
    private double quantityInGrams;

    @OneToOne
    private DailyMacro macro;

    @OneToOne
    private FoodItem item;

    public MacroFoodItem(double quantityInGrams, DailyMacro macro, FoodItem item) {
        this.quantityInGrams = quantityInGrams;
        this.macro = macro;
        this.item = item;
    }

    public MacroFoodItem() {
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

    public DailyMacro getMacro() {
        return macro;
    }

    public void setMacro(DailyMacro macro) {
        this.macro = macro;
    }

    public FoodItem getItem() {
        return item;
    }

    public void setItem(FoodItem item) {
        this.item = item;
    }
}
