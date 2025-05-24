package com.mercado.demo.service;

import com.mercado.demo.dto.purchase.CreatePurchaseDTO;
import com.mercado.demo.dto.purchase.GetPurchaseDTO;
import com.mercado.demo.entity.Purchase;
import com.mercado.demo.exception.PurchaseNotFoundException;
import com.mercado.demo.mapper.PurchaseMapper;
import com.mercado.demo.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseService {

    private final PurchaseRepository repository;
    private final PurchaseMapper mapper;

    public List<GetPurchaseDTO> getAllPurchases() {
        return repository.findAll().stream().map(mapper::teGetPurchaseDTO).toList();
    }

    public GetPurchaseDTO getPurchaseById(Long id) {
        return repository.findById(id).map(mapper::teGetPurchaseDTO)
                .orElseThrow(() -> new PurchaseNotFoundException("Purchase not found: " + id));
    }

    public GetPurchaseDTO createPurchase(CreatePurchaseDTO purchaseDTO) {
        var purchaseMapped = mapper.toPurchase(purchaseDTO);
        BigDecimal total = purchaseDTO.itens().stream().map(item ->
                item.price().multiply(item.quantity())).reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.DOWN);

        purchaseMapped.setDate(LocalDateTime.now());
        purchaseMapped.setTotal(total);
        repository.save(purchaseMapped);
        return mapper.teGetPurchaseDTO(purchaseMapped);
    }
}
