package com.example.inventory.controller;

import com.example.inventory.payload.ItemDTO;
import com.example.inventory.payload.ItemResponse;
import com.example.inventory.service.ItemService;
import com.example.inventory.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    //create item
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemDTO> createItem(@Valid @RequestBody ItemDTO itemDTO){
        return new ResponseEntity<>(itemService.createItem(itemDTO), HttpStatus.CREATED);
    }

    //get all items REST Api
    @GetMapping
    public ItemResponse getAllItems(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        return itemService.getAllItems(pageNo,pageSize,sortBy,sortDir);
    }

    //get item by id
    @GetMapping("/{id}")
    public  ResponseEntity<ItemDTO> getItemById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    //update item by id
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable(name = "id") long id){
        ItemDTO itemResponse = itemService.updateItem(itemDTO,id);
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //delete item REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable(name = "id") long id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
    }

}
