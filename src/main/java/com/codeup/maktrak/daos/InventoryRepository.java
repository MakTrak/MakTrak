package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.InventoryRecord;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<InventoryRecord, Long> {
}
