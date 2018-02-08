package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.DailyMacro;
import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.MacroFoodItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MacroItemRepository extends CrudRepository<MacroFoodItem, Long> {
    List<MacroFoodItem> findByItem(FoodItem item);
    List<MacroFoodItem> findByMacro(DailyMacro macro);
    MacroFoodItem findByMacroAndItem(DailyMacro macro, FoodItem item);
}
