package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.FoodItem;
import com.codeup.maktrak.models.InventoryRecord;
import com.codeup.maktrak.models.User;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<InventoryRecord, Long> {
    Iterable<InventoryRecord> findByOwner(User owner);
    InventoryRecord findByOwnerAndItem(User owner, FoodItem item);
}
