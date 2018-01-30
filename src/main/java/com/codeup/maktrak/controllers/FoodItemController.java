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
        return "food-items/edit-create";
    }

    @PostMapping("/food-item/create")
    @ResponseBody
    public String postFoodItem(@RequestParam(name = "serving") double serving, @RequestParam(name = "quantity") double quantity, @ModelAttribute FoodItem item) {
        item.setNutritionToPerGram(serving);
        itemDao.save(item);
        User user = userDao.findOne(1L); //CHANGE
        InventoryRecord invRec = new InventoryRecord(quantity, user, itemDao.findByName(item.getName()));
        invDao.save(invRec);
        return item.getName()+" "+invRec.getOwner().getFirstname();
    }
}
