package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new IllegalArgumentException("Нет такого продукта");
        } else {
            productBasket.addProduct(id);
        }
    }

    public UserBasket getUserBasket() {
        return new UserBasket(productBasket.showBasket().entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductById(entry.getKey()).orElseThrow(),entry.getValue()))
                .collect(Collectors.toList()));
    }
}
