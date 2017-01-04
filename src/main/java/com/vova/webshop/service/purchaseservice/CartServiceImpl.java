package com.vova.webshop.service.purchaseservice;

import com.vova.webshop.model.itemmodel.Item;
import com.vova.webshop.model.purchasemodel.Cart;
import com.vova.webshop.service.security.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Map;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    Cart cart;

    @Autowired
    PrincipalService principalService;

    @Override
    public void addItemsToCart(Item item, Integer quantity) {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        if (cart.getCartOwner().equals(principalService.getPrincipal())) {
            cart.addItems(item, quantity);
        }
    }

    @Override
    public Map<Item, Integer> getItemsInCart() {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        if (cart.getCartOwner().equals(principalService.getPrincipal())) {
            return cart.getItemsMap();
        } else {
            return null;
        }
    }

    @Override
    public BigInteger getItemsValue() {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        return cart.getAllItemsPrice();
    }

    @Override
    public void removeItem(Integer id) {
        if (cart.getCartOwner() == null) {
            setCartOwner();
        }
        if (cart.getCartOwner().equals(principalService.getPrincipal())) {
            cart.removeItem(id);
        }
    }

    private void setCartOwner() {
        if (cart.getCartOwner() == null) {
            cart.setCartOwner(principalService.getPrincipal());
        }
    }
}