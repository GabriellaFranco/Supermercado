package com.mercado.demo.dto.purchase;

import com.mercado.demo.dto.item.GetItemsDTO;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetPurchaseDTO(
        Long id,
        LocalDateTime date,
        BigDecimal total,
        List<GetItemsDTO> items
) {}

