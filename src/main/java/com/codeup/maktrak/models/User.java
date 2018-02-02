package com.codeup.maktrak.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String firstname;

    @Column
    private String lastname;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Recipe> recipeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<DailyMacro> macroList;



    public User(String username, String password, String email, String firstname, String lastname, List<Recipe> recipeList, List<DailyMacro> macroList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.recipeList = recipeList;
        this.macroList = macroList;
    }


    public User() {
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        firstname = copy.firstname;
        lastname = copy.lastname;
        password = copy.password;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<DailyMacro> getMacroList() {
        return macroList;
    }

    public void setMacroList(List<DailyMacro> macroList) {
        this.macroList = macroList;
    }

}
