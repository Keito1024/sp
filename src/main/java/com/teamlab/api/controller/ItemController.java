package com.teamlab.api.controller;


import com.teamlab.api.domain.Item;
import com.teamlab.api.dto.request.ItemCreateDto;
import com.teamlab.api.dto.request.ItemUpdateDto;
import com.teamlab.api.dto.response.ItemDto;
import com.teamlab.api.dto.response.ItemsDto;
import com.teamlab.api.exceptions.BadRequestException;
import com.teamlab.api.exceptions.NotFoundException;
import com.teamlab.api.services.ItemService;
import com.teamlab.api.services.ItemUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/v1/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private ItemService itemService;
    private ItemUpdateService itemUpdateService;

    @Autowired
    public ItemController(ItemService itemService, ItemUpdateService itemUpdateService) {
        this.itemService = itemService;
        this.itemUpdateService = itemUpdateService;

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Item> index() throws NotFoundException {
        List<Item> items = itemService.findAll();
        if (items.size() == 0) {
            throw new NotFoundException();
        }
        return items;
    }

    @RequestMapping(params = "id", method = RequestMethod.GET)
    public ResponseEntity<ItemDto> findById(@RequestParam("id") long id) {
        return new ResponseEntity<>(ItemDto.of(findProductByIdOrElseThrow(id)), HttpStatus.OK);
    }

    @RequestMapping(params = "q", method = RequestMethod.GET)
    public ResponseEntity<ItemsDto> searchItem(@RequestParam("q") String title) {
        if (title.isEmpty()) {
            return new ResponseEntity<>(ItemsDto.createEmpty(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ItemsDto(itemService.findByTitleLike(title).map(ItemDto::of).collect(Collectors.toList())), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ItemDto> create(@Validated ItemCreateDto itemCreateDto, BindingResult result) throws BadRequestException {
        //不正リクエスト
        if (result.hasErrors()) {
            throw new BadRequestException(getErrorMessages(result));
        }
        Item item = itemService.create(itemCreateDto.fill());
        return new ResponseEntity<>(ItemDto.of(item), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@RequestParam("id") long id) {
        Item item = findProductByIdOrElseThrow(id);
        itemService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ItemDto> update(@RequestParam("id") long id, @Validated ItemUpdateDto itemUpdateDto, BindingResult result) throws BadRequestException {
        if (result.hasErrors()) {
            throw new BadRequestException(getErrorMessages(result));
        }
        Item item = itemUpdateService.update(itemUpdateDto.update(findProductByIdOrElseThrow(id)));
        return new ResponseEntity<>(ItemDto.of(item), HttpStatus.OK);
    }


    //    このクラスでしか使わないよー
    private String getErrorMessages(BindingResult result) {
        return result.getAllErrors().stream().filter(Objects::nonNull).map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(File.separator));
    }

    private Item findProductByIdOrElseThrow(long id) {
        return itemService.findById(id).orElseThrow(NotFoundException::new);
    }

}
