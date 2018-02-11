package com.codeup.maktrak.controllers;

import com.codeup.maktrak.daos.FoodItemRepository;
import com.codeup.maktrak.daos.InventoryRepository;
import com.codeup.maktrak.daos.UserRepository;
import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.InventoryRecord;
import com.codeup.maktrak.models.User;
import com.codeup.maktrak.services.DaoOpService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class FoodItemController {
    private DaoOpService service;

    public FoodItemController(DaoOpService service) {
        this.service = service;
    }

    @GetMapping("/food-item/create")
    public String createFoodItem(Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "none");
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "");
        return "food-items/edit-create";
    }

    @GetMapping("/food-item/create/exists")
    public String createFoodItemExists(Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "none");
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "You already have an item with that name: Please try again");
        return "food-items/edit-create";
    }

    @PostMapping("/food-item/create")
    public String postFoodItem(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(service.foodNameExists(item, user)) {
            return "redirect:/food-item/create/exists";
        } else {
            service.createFoodItemInInventory(user, item, quantity);
            return "redirect:/food-item/inventory";
        }
    }

    @GetMapping("/food-item/edit/{id}")
    public String editFoodItemPage(@PathVariable long id, Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FoodItem item = service.findFoodItem(id);
        InventoryRecord invRec = service.findInventoryRecord(user, item);
        m.addAttribute("redirect", "none");
        m.addAttribute("item", item);
        m.addAttribute("mode", "edit");
        m.addAttribute("quantity", invRec.getQuantity());
        m.addAttribute("error", "");
        return "food-items/edit-create";
    }

    @GetMapping("/food-item/edit/{id}/exists")
    public String editFoodItemPageExists(@PathVariable long id, Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FoodItem item = service.findFoodItem(id);
        InventoryRecord invRec = service.findInventoryRecord(user, item);
        m.addAttribute("redirect", "none");
        m.addAttribute("item", item);
        m.addAttribute("mode", "edit");
        m.addAttribute("quantity", invRec.getQuantity());
        m.addAttribute("error", "You already have an item with that name: Please try again");
        return "food-items/edit-create";
    }

    @PostMapping("/food-item/edit/{id}")
    public String editFoodItemPost(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(service.foodNameEditExists(item, user)) {
            return "redirect:/food-item/edit/"+item.getId()+"/exists";
        } else {
            service.updateFoodItemInInventory(user, item, quantity);
            return "redirect:/food-item/inventory";
        }
    }

    @PostMapping("/food-item/delete/{id}")
    public String deleteFoodItem(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FoodItem item = service.findFoodItem(id);
        InventoryRecord invRec = service.findInventoryRecord(user, item);
        service.removeFoodItemInInventory(item, invRec);
        return "redirect:/food-item/inventory";
    }

    @PostMapping("/macro/create/food-item/delete/{id}")
    public String deleteFoodItemInMacro(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FoodItem item = service.findFoodItem(id);
        InventoryRecord invRec = service.findInventoryRecord(user, item);
        service.removeFoodItemInInventory(item, invRec);
        return "redirect:/macro/create";
    }

    @GetMapping("/food-item/inventory")
    public String showInventory(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<InventoryRecord> invRecs = service.findInventoryRecordsOfUser(user);
        m.addAttribute("invArr", invRecs);
        return "food-items/index";
    }

    @GetMapping("/recipe/create/food-item/create")
    public String createFoodItemInRecipe(Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "recipeCreate");
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "");
        return "food-items/edit-create";
    }

    @GetMapping("/recipe/create/food-item/create/exists")
    public String createFoodItemInRecipeExists(Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "recipeCreate");
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "You already have an item with that name: Please try again");
        return "food-items/edit-create";
    }

    @PostMapping("/recipe/create/food-item/create")
    public String postFoodItemInRecipe(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(service.foodNameExists(item, user)) {
            return "redirect:/recipe/create/food-item/create/exists";
        } else {
            service.createFoodItemInInventory(user, item, quantity);
            return "redirect:/recipe/create";
        }
    }

    @GetMapping("/recipe/edit/{id}/food-item/create")
    public String createFoodItemInEditRecipe(@PathVariable long id, Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "recipeEdit");
        m.addAttribute("recipeId", id);
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "");
        return "food-items/edit-create";
    }

    @GetMapping("/recipe/edit/{id}/food-item/create/exists")
    public String createFoodItemInEditRecipeExists(@PathVariable long id, Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "recipeEdit");
        m.addAttribute("recipeId", id);
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "You already have an item with that name: Please try again");
        return "food-items/edit-create";
    }

    @PostMapping("/recipe/edit/food-item/create")
    public String postFoodItemInEditRecipe(@RequestParam(name = "recipeId") long recipeId, @RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(service.foodNameExists(item, user)) {
            return "redirect:/recipe/edit/"+recipeId+"/food-item/create/exists";
        } else {
            service.createFoodItemInInventory(user, item, quantity);
            return "redirect:/recipe/edit/"+recipeId;
        }
    }

    @GetMapping("/macro/create/food-item/create")
    public String createFoodItemInMacro(Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "macroCreate");
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "");
        return "food-items/edit-create";
    }

    @GetMapping("/macro/create/food-item/create/exists")
    public String createFoodItemInMacroExists(Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "macroCreate");
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "You already have an item with that name: Please try again");
        return "food-items/edit-create";
    }

    @PostMapping("/macro/create/food-item/create")
    public String postFoodItemInMacro(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(service.foodNameExists(item, user)) {
            return "redirect:/macro/create/food-item/create/exists";
        } else {
            service.createFoodItemInInventory(user, item, quantity);
            return "redirect:/macro/create";
        }
    }

    @GetMapping("/macro/edit/{id}/food-item/create")
    public String createFoodItemInEditMacro(@PathVariable long id, Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "macroEdit");
        m.addAttribute("macroId", id);
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "");
        return "food-items/edit-create";
    }

    @GetMapping("/macro/edit/{id}/food-item/create/exists")
    public String createFoodItemInEditMacroExists(@PathVariable long id, Model m) {
        FoodItem item = new FoodItem();
        m.addAttribute("redirect", "macroEdit");
        m.addAttribute("macroId", id);
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        m.addAttribute("error", "You already have an item with that name: Please try again");
        return "food-items/edit-create";
    }

    @PostMapping("/macro/edit/food-item/create")
    public String postFoodItemInEditMacro(@RequestParam(name = "macroId") long macroId, @RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(service.foodNameExists(item, user)) {
            return "redirect:/macro/edit/"+macroId+"/food-item/create/exists";
        } else {
            service.createFoodItemInInventory(user, item, quantity);
            return "redirect:/macro/edit/"+macroId;
        }
    }
}
