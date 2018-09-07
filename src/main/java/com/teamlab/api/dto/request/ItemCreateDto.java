package com.teamlab.api.dto.request;

import com.teamlab.api.domain.Item;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ItemCreateDto {


    private Integer price;

    @NotBlank(message = "商品名を入力してください")
    @Size(max = 100, message = "100文字いないです")
    private String title;

    @NotBlank(message = "商品詳細を入力してください")
    @Size(max = 500, message = "500文字以内です")
    private String description;

    @NotBlank(message = "画像パスを入力してください")
    @Size(max = 500, message = "500文字いないです")
    private String img;

    public Item fill() {
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        return item;
    }
}
