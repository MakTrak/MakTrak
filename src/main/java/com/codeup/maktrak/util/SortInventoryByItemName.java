package com.codeup.maktrak.util;

import com.codeup.maktrak.models.InventoryRecord;

import java.util.Comparator;

public class SortInventoryByItemName implements Comparator<InventoryRecord> {
    public int compare(InventoryRecord a, InventoryRecord b)
    {
        return a.getItem().getName().compareTo(b.getItem().getName());
    }
}
