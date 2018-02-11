package com.codeup.maktrak.models;

import java.util.*;

public class MacroView {
    private long macroId;
    private String macroName;
    private double calGoals;
    private double carbGoals;
    private double fatGoals;
    private double protGoals;
    private double fiberGoals;
    private double calTotal;
    private double carbTotal;
    private double fatTotal;
    private double protTotal;
    private double fiberTotal;
    private ArrayList<String> itemNames;
    private ArrayList<Double> itemServings;
    private ArrayList<String> missingItemNames;
    private ArrayList<Double> missingItemGrams;
    private HashMap<String, Double> missingItemNameAndAmount;

    public MacroView(DailyMacro macro) {
        this.macroId = macro.getId();
        this.macroName = macro.getTitle();
        this.calGoals = macro.getCal();
        this.carbGoals = macro.getCarb();
        this.fatGoals = macro.getFat();
        this.protGoals = macro.getProt();
        this.fiberGoals = macro.getFiber();
        this.itemNames = new ArrayList<>();
        this.itemServings = new ArrayList<>();
        this.missingItemNames = new ArrayList<>();
        this.missingItemGrams = new ArrayList<>();
    }

    public MacroView() {
    }

    public long getMacroId() {
        return macroId;
    }

    public void setMacroId(long macroId) {
        this.macroId = macroId;
    }

    public String getMacroName() {
        return macroName;
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public double getCalGoals() {
        return calGoals;
    }

    public void setCalGoals(double calGoals) {
        this.calGoals = calGoals;
    }

    public double getCarbGoals() {
        return carbGoals;
    }

    public void setCarbGoals(double carbGoals) {
        this.carbGoals = carbGoals;
    }

    public double getFatGoals() {
        return fatGoals;
    }

    public void setFatGoals(double fatGoals) {
        this.fatGoals = fatGoals;
    }

    public double getProtGoals() {
        return protGoals;
    }

    public void setProtGoals(double protGoals) {
        this.protGoals = protGoals;
    }

    public double getFiberGoals() {
        return fiberGoals;
    }

    public void setFiberGoals(double fiberGoals) {
        this.fiberGoals = fiberGoals;
    }

    public double getCalTotal() {
        return calTotal;
    }

    public void setCalTotal(double calTotal) {
        this.calTotal = calTotal;
    }

    public double getCarbTotal() {
        return carbTotal;
    }

    public void setCarbTotal(double carbTotal) {
        this.carbTotal = carbTotal;
    }

    public double getFatTotal() {
        return fatTotal;
    }

    public void setFatTotal(double fatTotal) {
        this.fatTotal = fatTotal;
    }

    public double getProtTotal() {
        return protTotal;
    }

    public void setProtTotal(double protTotal) {
        this.protTotal = protTotal;
    }

    public double getFiberTotal() {
        return fiberTotal;
    }

    public void setFiberTotal(double fiberTotal) {
        this.fiberTotal = fiberTotal;
    }

    public ArrayList<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(ArrayList<String> itemNames) {
        this.itemNames = itemNames;
    }

    public ArrayList<Double> getItemServings() {
        return itemServings;
    }

    public void setItemServings(ArrayList<Double> itemServings) {
        this.itemServings = itemServings;
    }

    public HashMap<String, Double> getMissingItemNameAndAmount() {
        return missingItemNameAndAmount;
    }

    public void setMissingItemNameAndAmount(HashMap<String, Double> missingItemNameAndAmount) {
        this.missingItemNameAndAmount = missingItemNameAndAmount;
        Set<String> nameSet = this.missingItemNameAndAmount.keySet();
        for(String name : nameSet) {
            missingItemNames.add(name);
            missingItemGrams.add(Math.round(this.missingItemNameAndAmount.get(name)*100d)/100d);
        }
    }

    public ArrayList<String> getMissingItemNames() {
        return missingItemNames;
    }

    public void setMissingItemNames(ArrayList<String> missingItemNames) {
        this.missingItemNames = missingItemNames;
    }

    public ArrayList<Double> getMissingItemGrams() {
        return missingItemGrams;
    }

    public void setMissingItemGrams(ArrayList<Double> missingItemGrams) {
        this.missingItemGrams = missingItemGrams;
    }
}
