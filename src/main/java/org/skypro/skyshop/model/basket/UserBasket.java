package org.skypro.skyshop.model.basket;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserBasket that = (UserBasket) o;
        return total == that.total && Objects.equals(basketItemList, that.basketItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketItemList, total);
    }
}
