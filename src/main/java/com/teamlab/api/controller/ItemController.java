package com.teamlab.api.controller;


import com.teamlab.api.domain.Item;
import com.teamlab.api.dto.request.ItemCreateDto;
import com.teamlab.api.dto.response.ItemDto;
import com.teamlab.api.exceptions.BadRequestException;
import com.teamlab.api.exceptions.NotFoundException;
import com.teamlab.api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;

    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Item> index() throws NotFoundException {
        List<Item> items = itemService.findAll();
        if (items.size() == 0) {
            throw new NotFoundException("ありません");
        }
        return items;
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public Item show(@RequestParam("id") long id) throws NotFoundException {
        Item item = itemService.findById(id);
        return item;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ItemDto> create(ItemCreateDto itemCreateDto, BindingResult result) throws BadRequestException {

        //不正リクエスト
        if (result.hasErrors()) {
            throw new BadRequestException(getErrorMessages(result));
        }
        Item item = itemService.create(itemCreateDto.fill());
        return new ResponseEntity<>(ItemDto.of(item), HttpStatus.CREATED);
    }

    private String getErrorMessages(BindingResult result) {
        return result.getAllErrors().stream().filter(Objects::nonNull).map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(File.separator));
    }

}
