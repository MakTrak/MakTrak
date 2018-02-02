package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.Recipe;
import com.codeup.maktrak.models.RecipeFoodItem;
import org.springframework.data.repository.CrudRepository;

public interface RecipeItemRepository extends CrudRepository<RecipeFoodItem, Long> {
    double findByRecipeAndItem(Recipe recipe, FoodItem item);
}
