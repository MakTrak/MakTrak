package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.DailyMacro;
import com.codeup.maktrak.models.MacroRecipe;
import com.codeup.maktrak.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MacroRecipeRepository extends CrudRepository<MacroRecipe, Long> {
    List<MacroRecipe> findByMacro(DailyMacro macro);
    MacroRecipe findByMacroAndRecipe(DailyMacro macro, Recipe recipe);
    Iterable<MacroRecipe> findByRecipe(Recipe recipe);
}
