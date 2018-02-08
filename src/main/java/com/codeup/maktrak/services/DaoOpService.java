package com.codeup.maktrak.services;

import com.codeup.maktrak.daos.*;
import com.codeup.maktrak.models.*;
import com.codeup.maktrak.util.SortByItemName;
import com.codeup.maktrak.util.SortByRecipeItemItemName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
        System.out.println(invRec.getId());
        invRec.setQuantity(quantity);
        invDao.save(invRec);
    }

    public void removeFoodItemInInventory(FoodItem item, InventoryRecord invRec) {
        invDao.delete(invRec);
        recipeItemDao.delete(recipeItemDao.findByItem(item));
        macItemDao.delete(macItemDao.findByItem(item));
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
    public void createNewRecipe(User user, List<Double> itemQuantities, List<Long> foodIds, Recipe recipe, double servings) {
        recipe.setOwner(user);
        recipeDao.save(recipe);
        RecipeFoodItem newRecipeFoodItem;
        for(int i = 0; i < foodIds.size(); i++) {
            if(itemQuantities.get(i) > 0) {
                newRecipeFoodItem = new RecipeFoodItem(itemQuantities.get(i)/servings, recipeDao.findByTitle(recipe.getTitle()), itemDao.findOne(foodIds.get(i)));
                recipeItemDao.save(newRecipeFoodItem);
            }
        }
    }

    public void saveExistingRecipe(User user, List<Double> itemQuantities, List<Long> foodIds, Recipe recipe, double servings) {
        recipe.setOwner(user);
        recipeDao.save(recipe);
        RecipeFoodItem newRecipeFoodItem;
        for(int i = 0; i < foodIds.size(); i++) {
            if(itemQuantities.get(i) > 0) {
                if(recipeItemDao.findByRecipeAndItem(recipe, itemDao.findOne(foodIds.get(i))) != null) {
                    newRecipeFoodItem = recipeItemDao.findByRecipeAndItem(recipe, itemDao.findOne(foodIds.get(i)));
                    newRecipeFoodItem.setQuantityInGrams(itemQuantities.get(i)/servings);
                    recipeItemDao.save(newRecipeFoodItem);
                } else {
                    newRecipeFoodItem = new RecipeFoodItem(itemQuantities.get(i)/servings, recipeDao.findByTitle(recipe.getTitle()), itemDao.findOne(foodIds.get(i)));
                    recipeItemDao.save(newRecipeFoodItem);
                }
            } else if(itemQuantities.get(i) == 0) {
                newRecipeFoodItem = recipeItemDao.findByRecipeAndItem(recipe, itemDao.findOne(foodIds.get(i)));
                if(newRecipeFoodItem != null) {
                    recipeItemDao.delete(newRecipeFoodItem);
                }
            }
        }
    }

    public List<Recipe> findRecipesByOwner(User user) {
        return recipeDao.findByOwner(user);
    }

    public Recipe findRecipeById(long id) {
        return recipeDao.findOne(id);
    }

    public List<RecipeFoodItem> findRecipeFoodItemsInRecipe(Recipe recipe) {
        List<RecipeFoodItem> retval = recipeItemDao.findByRecipe(recipe);
        Collections.sort(retval, new SortByRecipeItemItemName());
        return retval;
    }

    public ArrayList<FoodItem> findFoodItemsInRecipe(Recipe recipe) {
        List<RecipeFoodItem> recItems = findRecipeFoodItemsInRecipe(recipe);
        ArrayList<FoodItem> retval = new ArrayList<>();
        for(RecipeFoodItem recItem : recItems) {
            retval.add(recItem.getItem());
        }
        Collections.sort(retval, new SortByItemName());
        return retval;
    }

    public ArrayList<FoodItem> findFoodItemsNotInRecipe(Recipe recipe, User user) {
        ArrayList<FoodItem> recItems = findFoodItemsInRecipe(recipe);
        ArrayList<FoodItem> items = findFoodItemsOwnedByUser(user);
        ArrayList<FoodItem> retval = new ArrayList<>();
        for(FoodItem item : items) {
            if(!recItems.contains(item)) {
                retval.add(item);
            }
        }
        Collections.sort(retval, new SortByItemName());
        return retval;
    }

    public HashMap<Recipe, List<RecipeFoodItem>> findRecipeAndFoodsByUser(User user) {
        List<Recipe> recipes = findRecipesByOwner(user);
        HashMap<Recipe, List<RecipeFoodItem>> retval = new HashMap<>();
        for(Recipe recipe : recipes) {
            retval.put(recipe, findRecipeFoodItemsInRecipe(recipe));
        }
        return retval;
    }

    public void removeRecipe(Recipe recipe) {
        Iterable<RecipeFoodItem> recItems = recipeItemDao.findByRecipe(recipe);
        recipeItemDao.delete(recItems);
        recipeDao.delete(recipe);
        //TODO need to remove macro-recipe here too!
    }

//    public HashMap<Recipe, List<FoodItem>> findFoodsInRecipeByUser(User user) {
//        List<Recipe> recipes = findRecipesByOwner(user);
//        HashMap<Recipe, List<FoodItem>> retval = new HashMap<>();
//        for(Recipe recipe : recipes) {
//            retval.put(recipe, findFoodItemsInRecipe(recipe));
//        }
//        return retval;
//    }
//
//    public double findRecipeFoodAmount(User user, FoodItem item, Recipe recipe) {
//        recipe.setOwner(user);
//        return recipeItemDao.findByRecipeAndItem(recipe, item).getQuantityInGrams();
//    }

    //Macro Related
    public DailyMacro addMacro(DailyMacro macro) {
        return macroDao.save(macro);
    }

    public List<DailyMacro> findMacrosByUser(User user) {
        return macroDao.findByOwner(user);
    }

    public ArrayList<MacroView> findMacroDetails(User user) {
        ArrayList<MacroView> retval = new ArrayList<>();
        List<DailyMacro> macros = findMacrosByUser(user);
        for(DailyMacro macro : macros) {
            MacroView macView = new MacroView(macro);
            double totalCal = 0;
            double totalCarb = 0;
            double totalProt = 0;
            double totalFiber = 0;
            double totalFat = 0;
            List<MacroFoodItem> macItems = findMacroItemByMacro(macro);
            ArrayList<String> itemNames = new ArrayList<>();
            HashMap<String, Double> missingItemsAndAmount = new HashMap<>();
            for(MacroFoodItem macItem : macItems) {
                totalCal += macItem.getItem().getCal();
                totalCarb += macItem.getItem().getCarb();
                totalProt += macItem.getItem().getProt();
                totalFiber += macItem.getItem().getFiber();
                totalFat += macItem.getItem().getFat();
                itemNames.add(macItem.getItem().getName());
                InventoryRecord invRec = invDao.findByOwnerAndItem(user, macItem.getItem());
                if(macItem.getQuantityInGrams() > invRec.getQuantity()) {
                    double missingAmount = (macItem.getQuantityInGrams() - invRec.getQuantity())*(macItem.getItem().getServingSizeInGrams());
                    missingItemsAndAmount.put(macItem.getItem().getName(), missingAmount);
                }
            }
            ArrayList<RecipeView> recViews = findRecViewByMacro(macro);
            for(RecipeView recView : recViews) {
                totalCal += recView.getTotalCal();
                totalCarb += recView.getTotalCarb();
                totalProt += recView.getTotalProt();
                totalFiber += recView.getTotalFiber();
                totalFat += recView.getTotalFat();
                itemNames.add("(Recipe) "+recView.getTitle());
            }
            macView.setCalTotal(totalCal);
            macView.setCarbTotal(totalCarb);
            macView.setProtTotal(totalProt);
            macView.setFiberTotal(totalFiber);
            macView.setFatTotal(totalFat);
            macView.setItemNames(itemNames);
            retval.add(macView);
        }
        return retval;
    }


    public DailyMacro findMacroById(long id) {
        return macroDao.findOne(id);
    }

    public MacroFoodItem addMacroItem(DailyMacro macro, FoodItem item, double quantity) {
        MacroFoodItem macItem = new MacroFoodItem(quantity, macro, item);
        return macItemDao.save(macItem);
    }

    public MacroFoodItem editAddMacroItem(DailyMacro macro, FoodItem item, double quantity) {
        MacroFoodItem macItem = macItemDao.findByMacroAndItem(macro, item);
        if(macItem != null) {
            macItem.setQuantityInGrams(quantity);
            macItemDao.save(macItem);
            return macItem;
        } else {
            return addMacroItem(macro, item, quantity);
        }
    }

    public void removeMacroItem(DailyMacro macro, FoodItem item) {
        MacroFoodItem macItem = macItemDao.findByMacroAndItem(macro, item);
        macItemDao.delete(macItem);
    }

    public List<MacroFoodItem> findMacroItemByMacro(DailyMacro macro) {
        return macItemDao.findByMacro(macro);
    }

    public MacroRecipe addMacroRecipe(DailyMacro macro, Recipe recipe, double servings) {
        MacroRecipe macRecipe = new MacroRecipe(servings, macro, recipe);
        return macRecipeDao.save(macRecipe);
    }

    public MacroRecipe editAddMacroRecipe(DailyMacro macro, Recipe recipe, double servings) {
        MacroRecipe macRec = macRecipeDao.findByMacroAndRecipe(macro, recipe);
        if(macRec != null) {
            macRec.setNumberOfServings(servings);
            macRecipeDao.save(macRec);
            return macRec;
        } else {
            return addMacroRecipe(macro, recipe, servings);
        }
    }

    public void removeMacroRecipe(DailyMacro macro, Recipe recipe) {
        MacroRecipe macRec = macRecipeDao.findByMacroAndRecipe(macro, recipe);
        macRecipeDao.delete(macRec);
    }

    public ArrayList<RecipeView> findRecViewByMacro(DailyMacro macro) {
        List<MacroRecipe> macRecs = macRecipeDao.findByMacro(macro);
        ArrayList<RecipeView> retval = new ArrayList<>();
        for(MacroRecipe macRec : macRecs) {
            retval.add(new RecipeView(macRec.getRecipe(), findRecipeFoodItemsInRecipe(macRec.getRecipe()), macRec.getNumberOfServings()));
        }
        return retval;
    }

    public void removeMacro(long macroId, User user) {
        DailyMacro macro = macroDao.findOne(macroId);
        macItemDao.delete(macItemDao.findByMacro(macro));
        macRecipeDao.delete(macRecipeDao.findByMacro(macro));
        macroDao.delete(macro);
    }

}
