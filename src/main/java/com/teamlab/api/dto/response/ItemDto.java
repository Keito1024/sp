package com.teamlab.api.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamlab.api.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    @JsonIgnore
    @Delegate
    private Item item;

    public static ItemDto of(Item item) {
        return new ItemDto(item);
    }
}
