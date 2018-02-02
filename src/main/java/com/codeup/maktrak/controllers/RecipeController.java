package com.codeup.maktrak.controllers;

import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.Recipe;
import com.codeup.maktrak.models.User;
import com.codeup.maktrak.services.DaoOpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        User user = service.findUser(1L);
        m.addAttribute("items", service.findFoodItemsOwnedByUser(user));
        m.addAttribute("service", service);
        return "recipe/edit-create";
    }

    @PostMapping("/recipe/create")
    public String postNewRecipe(@RequestParam(name = "itemQuantity") List<Double> itemQuantity, @RequestParam(name = "itemIds") List<Long> selectedItemIds, @ModelAttribute Recipe recipe) {
        User user = service.findUser(1L);
        service.createNewRecipe(user, itemQuantity, selectedItemIds, recipe);
        for(int i = 0; i < selectedItemIds.size(); i++) {
            System.out.println("Qty: "+itemQuantity.get(i)+"; ID: "+selectedItemIds.get(i)+";");
        }
        return "redirect:/recipe/index";
    }
}
