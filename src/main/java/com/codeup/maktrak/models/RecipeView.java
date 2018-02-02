package com.codeup.maktrak.models;

import java.util.ArrayList;
import java.util.List;

public class RecipeView {
    private long id;
    private String title;
    private int minsToPrep;
    private String description;
    private User owner;
    private List<RecipeFoodItem> recItems;
    private ArrayList<FoodItem> items;

    public RecipeView(Recipe recipe, List<RecipeFoodItem> recItems) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.minsToPrep = recipe.getMinsToPrep();
        this.description = recipe.getDescription();
        this.owner = recipe.getOwner();
        this.recItems = recItems;
        this.items = new ArrayList<>();
        for(RecipeFoodItem recItem : recItems) {
            items.add(recItem.getItem());
        }
    }

    public double getTotalCal() {
        double retval = 0;
        for(RecipeFoodItem item : this.recItems) {
            retval += item.getItem().getCal()*item.getQuantityInGrams();
        }
        return retval;
    }

    public double getTotalCarb() {
        double retval = 0;
        for(RecipeFoodItem item : this.recItems) {
            retval += item.getItem().getCarb()*item.getQuantityInGrams();
        }
        return retval;
    }

    public double getTotalProt() {
        double retval = 0;
        for(RecipeFoodItem item : this.recItems) {
            retval += item.getItem().getProt()*item.getQuantityInGrams();
        }
        return retval;
    }

    public double getTotalFat() {
        double retval = 0;
        for(RecipeFoodItem item : this.recItems) {
            retval += item.getItem().getFat()*item.getQuantityInGrams();
        }
        return retval;
    }

    public double getTotalFiber() {
        double retval = 0;
        for(RecipeFoodItem item : this.recItems) {
            retval += item.getItem().getFiber()*item.getQuantityInGrams();
        }
        return retval;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMinsToPrep() {
        return minsToPrep;
    }

    public void setMinsToPrep(int minsToPrep) {
        this.minsToPrep = minsToPrep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<RecipeFoodItem> getRecItems() {
        return recItems;
    }

    public void setRecItems(List<RecipeFoodItem> recItems) {
        this.recItems = recItems;
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<FoodItem> items) {
        this.items = items;
    }
}
