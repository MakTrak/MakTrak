package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.Recipe;
import com.codeup.maktrak.models.RecipeFoodItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeItemRepository extends CrudRepository<RecipeFoodItem, Long> {
    RecipeFoodItem findByRecipeAndItem(Recipe recipe, FoodItem item);
    List<RecipeFoodItem> findByRecipe(Recipe recipe);
    List<RecipeFoodItem> findByItem(FoodItem item);
}
