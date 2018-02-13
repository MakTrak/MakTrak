package com.codeup.maktrak.controllers;

import com.codeup.maktrak.models.*;
import com.codeup.maktrak.services.DaoOpService;
import com.codeup.maktrak.util.SortByItemName;
import com.codeup.maktrak.util.SortInventoryByItemName;
import com.codeup.maktrak.util.SortRecipeByName;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
        Collections.sort(recipes, new SortRecipeByName());
        HashMap<Recipe, List<RecipeFoodItem>> foodItemPreview = service.findRecipeAndFoodsByUser(user);
        ArrayList<RecipeView> recViews = new ArrayList<>();
        for(Recipe recipe : recipes) {
            List<RecipeFoodItem> recItems = foodItemPreview.get(recipe);
            recViews.add(new RecipeView(recipe, recItems));
        }
        m.addAttribute("recViews", recViews);
        List<InventoryRecord> invRecs = service.findInventoryRecordsOfUser(user);
        Collections.sort(invRecs, new SortInventoryByItemName());
        m.addAttribute("invArr", invRecs);
        DailyMacro macros = new DailyMacro();
        m.addAttribute("macros", macros);
        m.addAttribute("mode", "add");
        return "macro/edit-create";
    }

    @PostMapping("/macro/create")
    public String createMacroPost(@RequestParam(name = "item") List<Long> itemIds, @RequestParam(name = "itemAmount") List<Double> itemQuantities, @RequestParam(name = "recipe") List<Long> recipeIds, @RequestParam(name = "recipeAmount") List<Double> recipeQuantities, @ModelAttribute DailyMacro macros) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        macros.setOwner(user);
        DailyMacro newMarco = service.addMacro(macros);
        for(int i = 0; i < itemIds.size(); i++) {
            if(itemIds.get(i) != -1) {
                service.addMacroItem(newMarco, service.findFoodItem(itemIds.get(i)), itemQuantities.get(i));
            }
        }
        for(int i = 0; i < recipeIds.size(); i++) {
            if(recipeIds.get(i) != -1) {
                service.addMacroRecipe(newMarco, service.findRecipeById(recipeIds.get(i)), recipeQuantities.get(i));
            }
        }
        return "redirect:/macro/inventory";
    }

    @GetMapping("/macro/edit/{id}")
    public String editMacroForm(@PathVariable long id, Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DailyMacro macros = service.findMacroById(id);
        List<MacroFoodItem> macroItems = service.findMacroItemByMacro(macros);
        List<RecipeView> recViewsRoutine = service.findRecViewByMacro(macros);
        List<Recipe> recipes = service.findRecipesByOwner(user);
        HashMap<Recipe, List<RecipeFoodItem>> foodItemPreview = service.findRecipeAndFoodsByUser(user);
        ArrayList<RecipeView> recViews = new ArrayList<>();
        for(Recipe recipe : recipes) {
            List<RecipeFoodItem> recItems = foodItemPreview.get(recipe);
            recViews.add(new RecipeView(recipe, recItems));
        }
        Iterable<InventoryRecord> invRecs = service.findInventoryRecordsOfUser(user);
        m.addAttribute("recViews", recViews);
        m.addAttribute("invArr", invRecs);
        m.addAttribute("macros", macros);
        m.addAttribute("macroItems", macroItems);
        m.addAttribute("recViewsRoutine", recViewsRoutine);
        m.addAttribute("mode", "edit");
        return "macro/edit-create";
    }

    @PostMapping("/macro/edit")
    public String editMacroPost(@RequestParam(name = "item") List<Long> itemIds, @RequestParam(name = "itemAmount") List<Double> itemQuantities, @RequestParam(name = "recipe") List<Long> recipeIds, @RequestParam(name = "recipeAmount") List<Double> recipeQuantities, @RequestParam(name = "macroId") Long macroId, @ModelAttribute DailyMacro macros) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        macros.setOwner(user);
        macros.setId(macroId);
        DailyMacro editedMarco = service.addMacro(macros);
        for(int i = 0; i < itemIds.size(); i++) {
            if(itemIds.get(i) != -1) {
                if(itemQuantities.get(i) > 0) {
                    service.editAddMacroItem(editedMarco, service.findFoodItem(itemIds.get(i)), itemQuantities.get(i));
                } else {
                    service.removeMacroItem(editedMarco, service.findFoodItem(itemIds.get(i)));
                }
            }
        }
        for(int i = 0; i < recipeIds.size(); i++) {
            if(recipeIds.get(i) != -1) {
                if(recipeQuantities.get(i) > 0) {
                    service.editAddMacroRecipe(editedMarco, service.findRecipeById(recipeIds.get(i)), recipeQuantities.get(i));
                } else {
                    service.removeMacroRecipe(editedMarco, service.findRecipeById(recipeIds.get(i)));
                }
            }
        }
        return "redirect:/macro/inventory";
    }

    @PostMapping("/macro/delete")
    public String deleteMacro(@RequestParam(name = "macroId") Long macroId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.removeMacro(macroId, user);
        return "redirect:/macro/inventory";
    }

    @GetMapping("/macro/inventory")
    public String showMacros(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<MacroView> macViews = service.findMacroDetails(user);
        m.addAttribute("macViews", macViews);
        return "macro/index";
    }
}
