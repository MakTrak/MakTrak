package com.codeup.maktrak.models;

import javax.persistence.*;

@Entity(name = "recipes")
public class Recipe {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int minsToPrep;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Recipe(String title, int minsToPrep, User owner) {
        this.title = title;
        this.minsToPrep = minsToPrep;
        this.owner = owner;
    }

    public Recipe() {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
