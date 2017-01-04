package com.vova.webshop.service.purchaseservice;

import com.vova.webshop.model.itemmodel.Item;
import com.vova.webshop.model.usermodel.User;

import java.util.Map;

public interface PurchaseService {

    String purchaseAllCartItems(User user, Map<Item, Integer> itemsToBuyMap);
}