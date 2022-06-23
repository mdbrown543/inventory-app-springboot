package com.example.inventory.service;

import com.example.inventory.payload.ItemDTO;
import com.example.inventory.payload.ItemResponse;

import java.util.List;

public interface ItemService {
    ItemDTO createItem(ItemDTO itemdto);

   ItemResponse getAllItems(int pageNo, int pageSize, String sortBy, String sortDir);

    ItemDTO getItemById(long id);

    ItemDTO updateItem(ItemDTO itemDto, long id);

    void deleteItemById(long id);
}
