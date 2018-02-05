package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.Recipe;
import com.codeup.maktrak.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findByTitle(String title);
    List<Recipe> findByOwner(User user);
}
