package com.teamlab.api.controller;


import com.teamlab.api.domain.Item;
import com.teamlab.api.dto.ItemCreateDto;
import com.teamlab.api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;

    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Item> index() {
        List<Item> items = itemService.findAll();
        return items;
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Item show(@RequestParam("id") long id) {
        Item item = itemService.findById(id);
        return item;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(ItemCreateDto itemCreateDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Item item = itemService.create(itemCreateDto.fill());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

}
