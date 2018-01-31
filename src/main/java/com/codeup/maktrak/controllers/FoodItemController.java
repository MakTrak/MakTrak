package com.codeup.maktrak.controllers;

import com.codeup.maktrak.daos.FoodItemRepository;
import com.codeup.maktrak.daos.InventoryRepository;
import com.codeup.maktrak.daos.UserRepository;
import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.InventoryRecord;
import com.codeup.maktrak.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class FoodItemController {
    private UserRepository userDao;
    private FoodItemRepository itemDao;
    private InventoryRepository invDao;

    public FoodItemController(UserRepository userDao, FoodItemRepository itemDao, InventoryRepository invDao) {
        this.userDao = userDao;
        this.itemDao = itemDao;
        this.invDao = invDao;
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
        itemDao.save(item);
        User user = userDao.findOne(1L); //CHANGE
        InventoryRecord invRec = new InventoryRecord(quantity, user, itemDao.findByName(item.getName()));
        invDao.save(invRec);
        return "redirect:/food-item/inventory";
    }

    @GetMapping("/food-item/edit/{id}")
    public String editFoodItemPage(@PathVariable long id, Model m) {
        User user = userDao.findOne(1L);
        FoodItem item = itemDao.findOne(id);
        InventoryRecord invRec = invDao.findByOwnerAndItem(user, item);
        m.addAttribute("item", item);
        m.addAttribute("mode", "edit");
        m.addAttribute("quantity", invRec.getQuantity());
        return "food-items/edit-create";
    }

    @PostMapping("/food-item/edit/{id}")
    public String editFoodItemPost(@RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        itemDao.save(item);
        User user = userDao.findOne(1L);
        InventoryRecord invRec = invDao.findByOwnerAndItem(user, item);
        invRec.setQuantity(quantity);
        invDao.save(invRec);
        return "redirect:/food-item/inventory";
    }

    @GetMapping("/food-item/inventory")
    public String showInventory(Model m) {
        User user = userDao.findOne(1L);
        Iterable<InventoryRecord> invRecs = invDao.findByOwner(user);
        ArrayList<FoodItem> itemArr = new ArrayList<>();
        for(InventoryRecord invRec : invRecs) {
            itemArr.add(invRec.getItem());
        }
        m.addAttribute("items", itemArr);
        return "food-items/index";
    }
}
