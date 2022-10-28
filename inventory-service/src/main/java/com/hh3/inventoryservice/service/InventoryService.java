package com.hh3.inventoryservice.service;

import com.hh3.inventoryservice.repository.InventoryRepository;
import com.hh3.inventoryservice.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(item ->
            InventoryResponse.builder()
                    .skuCode(item.getSkuCode())
                    .isInStock(item.getQuantity() > 0)
                    .build()
        ).toList();
    }
}
