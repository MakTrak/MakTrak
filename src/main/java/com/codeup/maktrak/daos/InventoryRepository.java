package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.InventoryRecord;
import com.codeup.maktrak.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<InventoryRecord, Long> {
    List<InventoryRecord> findByOwner(User owner);
    InventoryRecord findByOwnerAndItem(User owner, FoodItem item);
    Iterable<InventoryRecord> findByItem(FoodItem item);
}
