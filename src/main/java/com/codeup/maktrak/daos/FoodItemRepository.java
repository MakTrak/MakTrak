package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.FoodItem;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {
    FoodItem findByName(String name);

}
