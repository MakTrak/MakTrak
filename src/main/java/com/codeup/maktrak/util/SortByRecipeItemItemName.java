package com.codeup.maktrak.util;

import com.codeup.maktrak.models.RecipeFoodItem;

import java.util.Comparator;

public class SortByRecipeItemItemName implements Comparator<RecipeFoodItem> {
    public int compare(RecipeFoodItem a, RecipeFoodItem b)
    {
        return a.getItem().getName().compareTo(b.getItem().getName());
    }
}
