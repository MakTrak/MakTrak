package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
