package com.vova.webshop.model.purchasemodel;

import com.vova.webshop.model.itemmodel.Item;
import com.vova.webshop.service.itemservice.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Item, Integer> itemsList = new HashMap<>();

    private String cartOwner;

    @Autowired
    ItemService itemService;

    public void addItems(Item item, Integer quantity) {
        if (itemsList.containsKey(item)) {
            Integer currentQuantity = itemsList.get(item);
            currentQuantity = currentQuantity + quantity;
            itemsList.put(item, currentQuantity);
        } else {
            itemsList.put(item, quantity);
        }
    }

    public Map<Item, Integer> getItemsMap() {
        return itemsList;
    }

    public BigInteger getAllItemsPrice() {
        BigInteger price = BigInteger.ZERO;

        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            price = price.add(entry.getKey().getPrice().multiply(BigInteger.valueOf(entry.getValue())));
        }

        return price;
    }

    public void removeItem(Integer id) {
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            if (entry.getKey().getId().equals(id)) {
                itemsList.remove(entry.getKey());
                break;
            }
        }
    }

    public String getCartOwner() {
        return cartOwner;
    }

    public void setCartOwner(String cartOwner) {
        this.cartOwner = cartOwner;
    }

    @PostConstruct
    public void init() {
    }

    @PreDestroy
    public void dest() {
        if(!itemsList.isEmpty()){
            for (Map.Entry<Item, Integer> entry : itemsList.entrySet()){
                Item item = itemService.findById(entry.getKey().getId());
                BigInteger newStock = item.getStock().add(BigInteger.valueOf(entry.getValue()));
                item.setStock(newStock);
                itemService.updateItem(item);
            }
        }
    }
}