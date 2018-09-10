package com.teamlab.api.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ItemsDto {

    private List<ItemDto> items;

    public static ItemsDto createEmpty() {
        return new ItemsDto(new ArrayList<>());
    }
}
