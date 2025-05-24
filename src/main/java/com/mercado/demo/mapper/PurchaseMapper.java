package com.mercado.demo.mapper;

import com.mercado.demo.dto.item.GetItemsDTO;
import com.mercado.demo.dto.purchase.CreatePurchaseDTO;
import com.mercado.demo.dto.purchase.GetPurchaseDTO;
import com.mercado.demo.entity.Item;
import com.mercado.demo.entity.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    public Purchase toPurchase(CreatePurchaseDTO purchaseDTO) {
        return Purchase.builder()
                .items(purchaseDTO.itens().stream().map(createItemsDTO -> Item.builder()
                        .type(createItemsDTO.type())
                        .price(createItemsDTO.price())
                        .quantity(createItemsDTO.quantity())
                        .description(createItemsDTO.description())
                        .build()).toList())
                .build();
    }

    public GetPurchaseDTO teGetPurchaseDTO(Purchase purchase) {
        return GetPurchaseDTO.builder()
                .id(purchase.getId())
                .date(purchase.getDate())
                .total(purchase.getTotal())
                .items(purchase.getItems().stream().map(item -> GetItemsDTO.builder()
                        .id(item.getId())
                        .type(item.getType())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .description(item.getDescription())
                        .build()).toList())
                .build();
    }
}
