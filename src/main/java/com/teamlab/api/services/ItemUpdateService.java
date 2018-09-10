package com.teamlab.api.services;

import com.teamlab.api.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class ItemUpdateService {

    private final ItemService itemService;

    @Autowired
    public ItemUpdateService(ItemService itemService) {
        this.itemService = itemService;
    }

    //更新処理記述
    @Transactional
    public Item update(Item item) {
        Item i = itemService.create(item);
        return i;
    }
}
