package com.codeup.maktrak.controllers;

import com.codeup.maktrak.daos.FoodItemRepository;
import com.codeup.maktrak.daos.InventoryRepository;
import com.codeup.maktrak.daos.UserRepository;
import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.InventoryRecord;
import com.codeup.maktrak.models.User;
import com.codeup.maktrak.services.DaoOpService;
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
        m.addAttribute("item", item);
        m.addAttribute("mode", "add");
        return "food-items/edit-create";
    }

    @PostMapping("/food-item/create")
    public String postFoodItem(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = service.findUser(1L); //CHANGE
        service.createFoodItemInInventory(user, item, quantity);
        return "redirect:/food-item/inventory";
    }

    @GetMapping("/food-item/edit/{id}")
    public String editFoodItemPage(@PathVariable long id, Model m) {
        User user = service.findUser(1L);
        FoodItem item = service.findFoodItem(id);
        InventoryRecord invRec = service.findInventoryRecord(user, item);
        m.addAttribute("item", item);
        m.addAttribute("mode", "edit");
        m.addAttribute("quantity", invRec.getQuantity());
        return "food-items/edit-create";
    }

    @PostMapping("/food-item/edit/{id}")
    public String editFoodItemPost(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        User user = service.findUser(1L);
        service.updateFoodItemInInventory(user, item, quantity);
        return "redirect:/food-item/inventory";
    }

    @PostMapping("/food-item/delete/{id}")
    public String deleteFoodItem(@PathVariable long id) {
        User user = service.findUser(1L);
        FoodItem item = service.findFoodItem(id);
        InventoryRecord invRec = service.findInventoryRecord(user, item);
        service.removeFoodItemInInventory(item, invRec);
        return "redirect:/food-item/inventory";
    }

    @GetMapping("/food-item/inventory")
    public String showInventory(Model m) {
        User user = service.findUser(1L);
        Iterable<InventoryRecord> invRecs = service.findInventoryRecordsOfUser(user);
        m.addAttribute("invArr", invRecs);
        return "food-items/index";
    }
}
