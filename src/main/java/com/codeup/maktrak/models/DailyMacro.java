package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity(name = "macros")
public class DailyMacro {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double cal;

    @Column(nullable = false)
    private double fat;

    @Column(nullable = false)
    private double carb;

    @Column(nullable = false)
    private double prot;

    @Column(nullable = false)
    private double fiber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public DailyMacro(String title, double cal, double fat, double carb, double prot, double fiber, User owner) {
        this.title = title;
        this.cal = cal;
        this.fat = fat;
        this.carb = carb;
        this.prot = prot;
        this.fiber = fiber;
        this.owner = owner;
    }

    public DailyMacro() {
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

    public double getCal() {
        return cal;
    }

    public void setCal(double cal) {
        this.cal = cal;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getProt() {
        return prot;
    }

    public void setProt(double prot) {
        this.prot = prot;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
