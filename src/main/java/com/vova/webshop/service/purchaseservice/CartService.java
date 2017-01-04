package com.vova.webshop.service.purchaseservice;

import com.vova.webshop.model.itemmodel.Item;

import java.math.BigInteger;
import java.util.Map;

public interface CartService {
    void addItemsToCart(Item item, Integer quantity);

    Map<Item, Integer> getItemsInCart();

    BigInteger getItemsValue();

    void removeItem(Integer itemId);
}