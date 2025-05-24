package com.mercado.demo.dto.purchase;

import com.mercado.demo.dto.item.CreateItemDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record CreatePurchaseDTO(
        List<CreateItemDTO> itens
) {}
