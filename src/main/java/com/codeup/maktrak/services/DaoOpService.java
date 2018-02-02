package com.codeup.maktrak.services;

import com.codeup.maktrak.daos.*;
import com.codeup.maktrak.models.*;
import com.codeup.maktrak.util.SortByItemName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DaoOpService {
    DailyMacroRepository macroDao;
    FoodItemRepository itemDao;
    InventoryRepository invDao;
    MacroItemRepository macItemDao;
    MacroRecipeRepository macRecipeDao;
    RecipeItemRepository recipeItemDao;
    RecipeRepository recipeDao;
    UserRepository userDao;
    WeeklyScheduleRepository weeklyDao;

    public DaoOpService(DailyMacroRepository macroDao, FoodItemRepository itemDao, InventoryRepository invDao, MacroItemRepository macItemDao, MacroRecipeRepository macRecipeDao, RecipeItemRepository recipeItemDao, RecipeRepository recipeDao, UserRepository userDao, WeeklyScheduleRepository weeklyDao) {
        this.macroDao = macroDao;
        this.itemDao = itemDao;
        this.invDao = invDao;
        this.macItemDao = macItemDao;
        this.macRecipeDao = macRecipeDao;
        this.recipeItemDao = recipeItemDao;
        this.recipeDao = recipeDao;
        this.userDao = userDao;
        this.weeklyDao = weeklyDao;
    }


    // User Related
    public User findUser(long id) {
        return userDao.findOne(id);
    }

    //Food & Inventory Related
    public void createFoodItemInInventory(User user, FoodItem item, double quantity) {
        itemDao.save(item);
        InventoryRecord invRec = new InventoryRecord(quantity, user, itemDao.findByName(item.getName()));
        invDao.save(invRec);
    }

    public void updateFoodItemInInventory(User user, FoodItem item, double quantity) {
        itemDao.save(item);
        InventoryRecord invRec = invDao.findByOwnerAndItem(user, item);
        invRec.setQuantity(quantity);
        invDao.save(invRec);
    }

    public void removeFoodItemInInventory(FoodItem item, InventoryRecord invRec) {
        invDao.delete(invRec);
        itemDao.delete(item);
    }

    public InventoryRecord findInventoryRecord(User user, FoodItem item) {
        return invDao.findByOwnerAndItem(user, item);
    }

    public Iterable<InventoryRecord> findInventoryRecordsOfUser(User user) {
        return invDao.findByOwner(user);
    }

    public ArrayList<FoodItem> findFoodItemsOwnedByUser(User user) {
        Iterable<InventoryRecord> invRecs = findInventoryRecordsOfUser(user);
        ArrayList<FoodItem> items = new ArrayList<>();
        for(InventoryRecord invRec : invRecs) {
            items.add(invRec.getItem());
        }
        Collections.sort(items, new SortByItemName());
        return items;
    }

    public FoodItem findFoodItem(long id) {
        return itemDao.findOne(id);
    }

    // Recipe & Food Related
    public void createNewRecipe(User user, List<Double> itemQuantities, List<Long> foodIds, Recipe recipe) {
        recipe.setOwner(user);
        recipeDao.save(recipe);
        RecipeFoodItem newRecipeFoodItem;
        for(int i = 0; i < foodIds.size(); i++) {
            newRecipeFoodItem = new RecipeFoodItem(itemQuantities.get(i), recipe, itemDao.findOne(foodIds.get(i)));
            recipeItemDao.save(newRecipeFoodItem);
        }
    }

    public double findRecipeFoodAmount(User user, FoodItem item, Recipe recipe) {
        recipe.setOwner(user);
        return recipeItemDao.findByRecipeAndItem(recipe, item);
    }
}
