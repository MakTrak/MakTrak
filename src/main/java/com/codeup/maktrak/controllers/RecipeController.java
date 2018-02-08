package com.codeup.maktrak.controllers;

import com.codeup.maktrak.models.*;
import com.codeup.maktrak.services.DaoOpService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = new Recipe();
        m.addAttribute("mode", "add");
        m.addAttribute("recipe", recipe);
        m.addAttribute("redirect", "none");
        m.addAttribute("items", service.findFoodItemsOwnedByUser(user));
        return "recipe/edit-create";
    }

    @PostMapping("/recipe/create")
    public String postNewRecipe(@RequestParam(name = "num-servings") double servings, @RequestParam(name = "itemQuantity") List<Double> itemQuantity, @RequestParam(name = "itemIds") List<Long> selectedItemIds, @ModelAttribute Recipe recipe) {
        if(itemQuantity.size() != selectedItemIds.size() || itemQuantity.contains(null)) {
            return "redirect:/recipe/create";
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.createNewRecipe(user, itemQuantity, selectedItemIds, recipe, servings);
            return "redirect:/recipe/inventory";
        }
    }

    @GetMapping("/recipe/edit/{id}")
    public String editRecipeForm(@PathVariable long id, Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = service.findRecipeById(id);
        m.addAttribute("mode", "edit");
        m.addAttribute("recipe", recipe);
        m.addAttribute("redirect", "none");
        m.addAttribute("itemsInRecipe", service.findRecipeFoodItemsInRecipe(recipe));
        m.addAttribute("itemsNotInRecipe", service.findFoodItemsNotInRecipe(recipe, user));
        return "recipe/edit-create";
    }

    @PostMapping("/recipe/edit/{id}")
    public String editRecipePost(@RequestParam(name = "num-servings") double servings, @RequestParam(name = "itemQuantity") List<Double> itemQuantity, @RequestParam(name = "itemIds") List<Long> selectedItemIds, @ModelAttribute Recipe recipe) {
        if(itemQuantity.size() != selectedItemIds.size() || itemQuantity.contains(null)) {
            return "redirect:/recipe/edit/"+recipe.getId();
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.saveExistingRecipe(user, itemQuantity, selectedItemIds, recipe, servings);
            return "redirect:/recipe/inventory";
        }
    }

    @PostMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable long id) {
        Recipe recipe = service.findRecipeById(id);
        service.removeRecipe(recipe);
        return "redirect:/recipe/inventory";
    }

    @PostMapping("/macro/create/recipe/delete/{id}")
    public String deleteRecipeInMacro(@PathVariable long id) {
        Recipe recipe = service.findRecipeById(id);
        service.removeRecipe(recipe);
        return "redirect:/macro/create";
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

    @GetMapping("/macro/create/recipe/create")
    public String createRecipeFormFromMacro(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = new Recipe();
        m.addAttribute("mode", "add");
        m.addAttribute("redirect", "macroCreate");
        m.addAttribute("recipe", recipe);
        m.addAttribute("items", service.findFoodItemsOwnedByUser(user));
        return "recipe/edit-create";
    }

    @PostMapping("/macro/create/recipe/create")
    public String postNewRecipeFromMacro(@RequestParam(name = "num-servings") double servings, @RequestParam(name = "itemQuantity") List<Double> itemQuantity, @RequestParam(name = "itemIds") List<Long> selectedItemIds, @ModelAttribute Recipe recipe) {
        if(itemQuantity.size() != selectedItemIds.size() || itemQuantity.contains(null)) {
            return "redirect:/macro/create/recipe/create";
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.createNewRecipe(user, itemQuantity, selectedItemIds, recipe, servings);
            return "redirect:/macro/create";
        }
    }

    @GetMapping("/macro/edit/{id}/recipe/create")
    public String createRecipeFormfromEditMacro(@PathVariable long id, Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = new Recipe();
        m.addAttribute("mode", "add");
        m.addAttribute("redirect", "macroEdit");
        m.addAttribute("macroId", id);
        m.addAttribute("recipe", recipe);
        m.addAttribute("items", service.findFoodItemsOwnedByUser(user));
        return "recipe/edit-create";
    }

    @PostMapping("/macro/edit/recipe/create")
    public String postNewRecipefromEditMacro(@RequestParam(name = "macroId") long macroId, @RequestParam(name = "num-servings") double servings, @RequestParam(name = "itemQuantity") List<Double> itemQuantity, @RequestParam(name = "itemIds") List<Long> selectedItemIds, @ModelAttribute Recipe recipe) {
        if(itemQuantity.size() != selectedItemIds.size() || itemQuantity.contains(null)) {
            return "redirect:/macro/edit/"+macroId+"/recipe/create";
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            service.createNewRecipe(user, itemQuantity, selectedItemIds, recipe, servings);
            return "redirect:/macro/edit/"+macroId;
        }
    }
}
