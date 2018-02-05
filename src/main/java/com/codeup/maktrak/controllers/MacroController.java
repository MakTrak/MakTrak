package com.codeup.maktrak.controllers;

import com.codeup.maktrak.models.*;
import com.codeup.maktrak.services.DaoOpService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "macro/edit-create";
    }
}
