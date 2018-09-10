package com.teamlab.api.services;


import com.teamlab.api.domain.Item;
import com.teamlab.api.repositories.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    public Stream<Item> findByTitleLike(String title){
        return itemRepository.findByTitleContainingOrderById(title).stream();
    }
    public Item create(Item item) {
        return itemRepository.saveAndFlush(item);
    }

    public Item delete(Item item) {
        itemRepository.delete(item);
        return item;
    }

}
