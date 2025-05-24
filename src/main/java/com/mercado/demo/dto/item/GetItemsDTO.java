package com.mercado.demo.dto.item;

import com.mercado.demo.enums.ItemType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GetItemsDTO(
        Long id,
        ItemType type,
        BigDecimal price,
        BigDecimal quantity,
        String description
) {}
