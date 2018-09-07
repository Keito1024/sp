package com.teamlab.api.dto;

import com.teamlab.api.domain.Item;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ItemCreateDto {

    private Integer price;

    private String title;

    private String description;

    private String img;

    public Item fill() {
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        return item;
    }
}
