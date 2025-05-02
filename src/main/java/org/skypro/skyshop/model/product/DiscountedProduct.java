package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final float price;
    private final int discount;

    public DiscountedProduct(UUID id, String name, float price, int discount) {
        super(id,name);
        checkPrice(price);
        this.price = price;
        checkDiscount(discount);
        this.discount = discount;
    }

    private void checkDiscount(int discount) throws IllegalArgumentException{
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Проценты должны быть от 0 до 100");
        }
    }

    private static void checkPrice(float price) throws IllegalArgumentException{
        if (price <= 0) {
            throw new IllegalArgumentException("Цена не должна быть отрицательной");
        }
    }

    @Override
    public float getPrice() {
        return price * (1 - (float)discount/100);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}

