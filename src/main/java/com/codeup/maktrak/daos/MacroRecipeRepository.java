package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.MacroRecipe;
import org.springframework.data.repository.CrudRepository;

public interface MacroRecipeRepository extends CrudRepository<MacroRecipe, Long> {
}
