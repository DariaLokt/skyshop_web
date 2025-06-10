package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;

    @Test
    public void givenNoSuchProduct_whenAdd_thenThrowsException() {
        Searchable searching = new SimpleProduct(UUID.randomUUID(), "A",5);
        Mockito.when(storageService.getProductById(searching.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProduct(searching.getId()));
    }

    @Test
    public void givenProductExists_whenAdd_thenExecutesTheMethod() {
        Product searching = new SimpleProduct(UUID.randomUUID(), "A",5);
        Mockito.when(storageService.getProductById(searching.getId())).thenReturn(Optional.of(searching));
        basketService.addProduct(searching.getId());
        Mockito.verify(productBasket).addProduct(searching.getId());
    }

    @Test
    public void givenProductBasketIsEmpty_whenGetUserBasket_thenReturnEmptyBasket() {
        Mockito.when(productBasket.showBasket()).thenReturn(Collections.emptyMap());
        Assertions.assertEquals(basketService.getUserBasket(),new UserBasket(Collections.emptyList()));
    }

    @Test
    public void givenProductBasketIsNotEmpty_whenGetUserBasket_thenReturnUserBasket() {
        Product searching = new SimpleProduct(UUID.randomUUID(), "A",5);
        UserBasket basket = new UserBasket(List.of(new BasketItem(searching,1)));
        Mockito.when(storageService.getProductById(searching.getId())).thenReturn(Optional.of(searching));
        Mockito.when(productBasket.showBasket()).thenReturn(Map.of(searching.getId(),1));
        Assertions.assertEquals(basketService.getUserBasket(),basket);
    }
}