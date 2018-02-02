package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.MacroFoodItem;
import org.springframework.data.repository.CrudRepository;

public interface MacroItemRepository extends CrudRepository<MacroFoodItem, Long> {
}
