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
public class MacroController {
    DaoOpService service;

    public MacroController(DaoOpService service) {
        this.service = service;
    }


    @GetMapping("/macro/create")
    public String createMacroForm(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Recipe> recipes = service.findRecipesByOwner(user);
        HashMap<Recipe, List<RecipeFoodItem>> foodItemPreview = service.findRecipeAndFoodsByUser(user);
        ArrayList<RecipeView> recViews = new ArrayList<>();
        for(Recipe recipe : recipes) {
            List<RecipeFoodItem> recItems = foodItemPreview.get(recipe);
            recViews.add(new RecipeView(recipe, recItems));
        }
        m.addAttribute("recViews", recViews);
        Iterable<InventoryRecord> invRecs = service.findInventoryRecordsOfUser(user);
        m.addAttribute("invArr", invRecs);
        DailyMacro macros = new DailyMacro();
        m.addAttribute("macros", macros);
        return "macro/edit-create";
    }

    @PostMapping("/macro/create")
    public String createMacroPost(@RequestParam(name = "item") List<Long> itemIds, @RequestParam(name = "itemAmount") List<Double> itemQuantities, @RequestParam(name = "recipe") List<Long> recipeIds, @RequestParam(name = "recipeAmount") List<Double> recipeQuantities, @ModelAttribute DailyMacro macros) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        macros.setOwner(user);
        DailyMacro newMarco = service.addMacro(macros);
        for(int i = 0; i < itemIds.size(); i++) {
            service.addMacroItem(newMarco, service.findFoodItem(itemIds.get(i)), itemQuantities.get(i));
        }
        for(int i = 0; i < recipeIds.size(); i++) {
            service.addMacroRecipe(newMarco, service.findRecipeById(recipeIds.get(i)), recipeQuantities.get(i));
        }
        return "home";
    }
}
