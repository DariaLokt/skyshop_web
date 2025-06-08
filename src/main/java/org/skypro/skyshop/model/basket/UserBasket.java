package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItemList;
    private final int total;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        this.total = getTotal();
    }

    int getTotal() {
        return basketItemList.stream()
                .mapToInt(basketItem -> (int)basketItem.getProduct().getPrice() * basketItem.getQuantity())
                .sum();
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }
}
