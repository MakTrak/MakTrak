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
    private ArrayList<Double> itemAmounts;
    private double macRecAmount;

    public RecipeView(Recipe recipe, List<RecipeFoodItem> recItems) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.minsToPrep = recipe.getMinsToPrep();
        this.description = recipe.getDescription();
        this.owner = recipe.getOwner();
        this.recItems = recItems;
        this.items = new ArrayList<>();
        this.itemAmounts = new ArrayList<>();
        for(RecipeFoodItem recItem : recItems) {
            items.add(recItem.getItem());
            itemAmounts.add(recItem.getQuantityInGrams()*recItem.getItem().getServingSizeInGrams());
        }
    }

    public RecipeView(Recipe recipe, List<RecipeFoodItem> recItems, double macRecAmount) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.minsToPrep = recipe.getMinsToPrep();
        this.description = recipe.getDescription();
        this.owner = recipe.getOwner();
        this.recItems = recItems;
        this.items = new ArrayList<>();
        this.itemAmounts = new ArrayList<>();
        for(RecipeFoodItem recItem : recItems) {
            items.add(recItem.getItem());
            itemAmounts.add(recItem.getQuantityInGrams()*recItem.getItem().getServingSizeInGrams());
        }
        this.macRecAmount = macRecAmount;
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

    public double getMacRecAmount() {
        return macRecAmount;
    }

    public void setMacRecAmount(double macRecAmount) {
        this.macRecAmount = macRecAmount;
    }

    public ArrayList<Double> getItemAmounts() {
        return itemAmounts;
    }

    public void setItemAmounts(ArrayList<Double> itemAmounts) {
        this.itemAmounts = itemAmounts;
    }
}
