package com.codeup.maktrak.util;

import com.codeup.maktrak.models.FoodItem;

import java.util.Comparator;

public class SortByItemName implements Comparator<FoodItem> {
    public int compare(FoodItem a, FoodItem b)
    {
        return a.getName().compareTo(b.getName());
    }
}
