package com.codeup.maktrak.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "recipes")
public class Recipe {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private int minsToPrep;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Recipe(String title, int minsToPrep, String description, User owner) {
        this.title = title;
        this.minsToPrep = minsToPrep;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
