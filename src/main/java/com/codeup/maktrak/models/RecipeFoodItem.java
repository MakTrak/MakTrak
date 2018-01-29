package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity(name = "recipe_item")
public class RecipeFoodItem {
    @Id @GeneratedValue
    private long id;

    @Column(name = "quantity", nullable = false)
    private double quantityInGrams;

    @OneToOne
    private Recipe recipe;

    @OneToOne
    private FoodItem item;

    public RecipeFoodItem(double quantityInGrams, Recipe recipe, FoodItem item) {
        this.quantityInGrams = quantityInGrams;
        this.recipe = recipe;
        this.item = item;
    }

    public RecipeFoodItem() {
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public FoodItem getItem() {
        return item;
    }

    public void setItem(FoodItem item) {
        this.item = item;
    }
}
