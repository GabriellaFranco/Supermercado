package com.mercado.demo.controller;

import com.mercado.demo.dto.purchase.CreatePurchaseDTO;
import com.mercado.demo.dto.purchase.GetPurchaseDTO;
import com.mercado.demo.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService service;

    @GetMapping
    public ResponseEntity<List<GetPurchaseDTO>> getAllPurchases() {
        List<GetPurchaseDTO> purchases = service.getAllPurchases();
        return purchases.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(purchases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPurchaseDTO> getPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPurchaseById(id));
    }

    @PostMapping
    public ResponseEntity<GetPurchaseDTO> createPurchase(@RequestBody @Valid CreatePurchaseDTO purchaseDTO) {
        var purchase =service.createPurchase(purchaseDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(purchase.id()).toUri();
        return ResponseEntity.created(uri).body(purchase);
    }
}
