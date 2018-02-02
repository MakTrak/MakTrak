package com.codeup.maktrak.controllers;

import com.codeup.maktrak.models.*;
import com.codeup.maktrak.services.DaoOpService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class RecipeController {
    DaoOpService service;

    public RecipeController(DaoOpService service) {
        this.service = service;
    }

    @GetMapping("/recipe/create")
    public String createRecipeForm(Model m) {
        Recipe recipe = new Recipe();
        m.addAttribute("recipe", recipe);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        m.addAttribute("items", service.findFoodItemsOwnedByUser(user));
        m.addAttribute("service", service);
        return "recipe/edit-create";
    }

    @PostMapping("/recipe/create")
    public String postNewRecipe(@RequestParam(name = "num-servings") double servings, @RequestParam(name = "itemQuantity") List<Double> itemQuantity, @RequestParam(name = "itemIds") List<Long> selectedItemIds, @ModelAttribute Recipe recipe) {
        if(itemQuantity.size() != selectedItemIds.size()) {
            return "redirect:/recipe/create";
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.createNewRecipe(user, itemQuantity, selectedItemIds, recipe, servings);
            return "redirect:/recipe/inventory";
        }
    }

    @GetMapping("/recipe/inventory")
    public String listAllRecipes(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Recipe> recipes = service.findRecipesByOwner(user);
        HashMap<Recipe, List<RecipeFoodItem>> foodItemPreview = service.findRecipeAndFoodsByUser(user);
        ArrayList<RecipeView> recViews = new ArrayList<>();
        for(Recipe recipe : recipes) {
            List<RecipeFoodItem> recItems = foodItemPreview.get(recipe);
            recViews.add(new RecipeView(recipe, recItems));
        }
        m.addAttribute("recViews", recViews);
        return "recipe/index";
    }
}
