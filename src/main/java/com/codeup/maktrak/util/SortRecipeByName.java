package com.codeup.maktrak.util;

import com.codeup.maktrak.models.Recipe;

import java.util.Comparator;

public class SortRecipeByName implements Comparator<Recipe> {
    public int compare(Recipe a, Recipe b)
    {
        return a.getTitle().compareTo(b.getTitle());
    }
}