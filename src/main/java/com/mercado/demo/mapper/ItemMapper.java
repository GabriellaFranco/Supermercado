package com.mercado.demo.mapper;

import com.mercado.demo.dto.item.GetItemsDTO;
import com.mercado.demo.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toItem(GetItemsDTO itemDTO) {
        return Item.builder()
                .id(itemDTO.id())
                .type(itemDTO.type())
                .price(itemDTO.price())
                .quantity(itemDTO.quantity())
                .description(itemDTO.description())
                .build();
    }

    public GetItemsDTO toGetItemsDTO(Item item) {
        return GetItemsDTO.builder()
                .id(item.getId())
                .type(item.getType())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .description(item.getDescription())
                .build();
    }
}
