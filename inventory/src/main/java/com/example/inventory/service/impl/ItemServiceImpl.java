package com.example.inventory.service.impl;

import com.example.inventory.entity.Item;
import com.example.inventory.exceptions.ResourceNotFoundException;
import com.example.inventory.payload.ItemResponse;
import com.example.inventory.repository.ItemRepository;
import com.example.inventory.payload.ItemDTO;
import com.example.inventory.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl  implements ItemService {
   private ItemRepository itemRepository;

   private ModelMapper mapper;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper mapper) {

        this.itemRepository = itemRepository;
        this.mapper = mapper;
    }

    @Override
    public ItemDTO createItem(ItemDTO itemdto) {
        //Convert DTO to entity
        Item item = mapToEntity(itemdto);

        //Old public method
       /* Item item = new Item();
        item.setTitle(itemdto.getTitle());
        item.setDescription(itemdto.getDescription());
        item.setCategory(itemdto.getCategory());
        item.setImage(itemdto.getImage());
        item.setPrice(itemdto.getPrice());*/

        Item newItem = itemRepository.save(item);

        //covert entity to DTO
        ItemDTO itemResponse = mapToDTO(newItem);

        //Old public method
        /*ItemDTO itemResponse = new ItemDTO();
        itemResponse.setId(newItem.getId());
        itemResponse.setDescription(newItem.getDescription());
        itemResponse.setTitle(newItem.getTitle());
        itemResponse.setPrice(newItem.getPrice());
        itemResponse.setCategory(newItem.getCategory());
        itemResponse.setImage(newItem.getImage());*/

        return itemResponse;
    }

    @Override
    public ItemResponse getAllItems(int pageNo, int pageSize,String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //create Pageable instance
        PageRequest newPage = PageRequest.of(pageNo,pageSize, sort);

        Page<Item> items = itemRepository.findAll(newPage);

        //get content from page object
        List<Item> listOfItems = items.getContent();
        List<ItemDTO> content =  listOfItems.stream().map(item -> mapToDTO(item)).collect(Collectors.toList());

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setContent(content);
        itemResponse.setPageNo(items.getNumber());
        itemResponse.setPageSize(items.getSize());
        itemResponse.setTotalElements(items.getTotalElements());
        itemResponse.setTotalPages(items.getTotalPages());
        itemResponse.setLast(items.isLast());

        return itemResponse;

    }

    @Override
    public ItemDTO getItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id",id));
        return mapToDTO(item);
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemdto, long id) {
        //get item by id from database
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id",id));
        item.setTitle(itemdto.getTitle());
        item.setDescription(itemdto.getDescription());
        item.setCategory(itemdto.getCategory());
        item.setImage(itemdto.getImage());
        item.setPrice(itemdto.getPrice());

        Item updatedItem = itemRepository.save(item);
        return mapToDTO(updatedItem);

    }

    @Override
    public void deleteItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item", "id",id));
        itemRepository.delete(item);
    }

    //convert Entity to DTO
    private ItemDTO mapToDTO(Item item){
        ItemDTO itemDto = mapper.map(item, ItemDTO.class);

        /*ItemDTO itemDto = new ItemDTO();
        itemDto.setId(item.getId());
        itemDto.setTitle(item.getTitle());
        itemDto.setDescription(item.getDescription());
        itemDto.setCategory(item.getCategory());
        itemDto.setPrice(item.getPrice());
        itemDto.setImage(item.getImage());*/

        return itemDto;
    }
    //convert DTO to entity
    private Item mapToEntity(ItemDTO itemdto){
        Item item = mapper.map(itemdto, Item.class);

        /*Item item = new Item();
        item.setTitle(itemdto.getTitle());
        item.setDescription(itemdto.getDescription());
        item.setCategory(itemdto.getCategory());
        item.setImage(itemdto.getImage());
        item.setPrice(itemdto.getPrice());*/

        return item;
    }
}
