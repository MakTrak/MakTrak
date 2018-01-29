package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity(name = "macro_recipe")
public class MacroRecipe {
    @Id @GeneratedValue
    private long id;

    @Column(name = "servings", nullable = false)
    private double numberOfServings;

    @OneToOne
    private DailyMacro macro;

    @OneToOne
    private Recipe recipe;

    public MacroRecipe(double numberOfServings, DailyMacro macro, Recipe recipe) {
        this.numberOfServings = numberOfServings;
        this.macro = macro;
        this.recipe = recipe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(double numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public DailyMacro getMacro() {
        return macro;
    }

    public void setMacro(DailyMacro macro) {
        this.macro = macro;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
