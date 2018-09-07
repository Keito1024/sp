package com.teamlab.api.services;


import com.teamlab.api.domain.Item;
import com.teamlab.api.repositories.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(long id) {
        Item item = itemRepository.findById(id);
        return item;
    }

    public Item create(Item item) {
        return itemRepository.saveAndFlush(item);
    }

    public Item delete(Item item) {
        itemRepository.delete(item);
        return item;
    }
}
