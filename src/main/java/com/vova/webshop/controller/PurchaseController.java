package com.vova.webshop.controller;

import com.vova.webshop.model.itemmodel.Item;
import com.vova.webshop.model.purchasemodel.Purchase;
import com.vova.webshop.model.usermodel.User;
import com.vova.webshop.service.itemservice.ItemService;
import com.vova.webshop.service.purchaseservice.CartService;
import com.vova.webshop.service.purchaseservice.PurchaseService;
import com.vova.webshop.service.security.PrincipalService;
import com.vova.webshop.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

@Controller
public class PurchaseController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserService userService;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PrincipalService principalService;

    @Autowired
    CartService cartService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/item/{id}/addtocart"}, method = RequestMethod.GET)
    public String addItemToCart(@PathVariable Integer id, ModelMap model) {
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }
        Item item = itemService.findById(id);
        String userName = principalService.getPrincipal();
        User user = userService.findBySSO(userName);
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        model.addAttribute("item", item);
        model.addAttribute("user", user);
        return "addtocart";
    }

    @RequestMapping(value = {"item/{id}/addtocart"}, method = RequestMethod.POST)
    public String addToCart(@Valid Purchase purchase, BindingResult result,
                            ModelMap modelMap) {

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);
        Item item = itemService.findById(purchase.getItemId());
        Integer quantity = purchase.getQuantity();
        BigInteger quantityBigInt = BigInteger.valueOf(purchase.getQuantity());

        modelMap.addAttribute("purchase", purchase);
        modelMap.addAttribute("item", item);
        modelMap.addAttribute("user", currentUser);

        if (result.hasErrors()) {
            return "addtocart";
        }

        if (item.getStock().compareTo(quantityBigInt) < 0) {
            FieldError ssoError = new FieldError("item", "stock", messageSource.getMessage(
                    "stock.too.large", new String[]{String.valueOf(item.getStock())}, Locale.getDefault()));
            result.addError(ssoError);
            return "addtocart";
        }

        BigInteger newStock = item.getStock().subtract(quantityBigInt);
        item.setStock(newStock);

        itemService.updateItem(item);

        cartService.addItemsToCart(item, quantity);

        modelMap.addAttribute("cartItems", cartService.getItemsInCart());
        modelMap.addAttribute("itemsValue", cartService.getItemsValue());
        modelMap.addAttribute("accountBalance", currentUser.getBalance());

        return "cart";
    }

    @RequestMapping(value = {"/buyall"}, method = RequestMethod.GET)
    public String purchaseItemsFromCart(ModelMap modelMap) {
        if (principalService.isCurrentAuthenticationAnonymous()) {
            return "login";
        }

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);

        Map<Item, Integer> itemsToBuy = new HashMap<>();
        //if (!cartService.getItemsInCart().isEmpty()) {
        //   itemsToBuy.putAll(cartService.getItemsInCart());//TODO
        //}

        if (cartService.getItemsValue().compareTo(currentUser.getBalance()) > 0) {
            //TODO error code not enough balance
            return "cart";
        }

        String result = purchaseService.purchaseAllCartItems(currentUser, itemsToBuy);

        modelMap.addAttribute("result", result);

        if (result.equals("success")) {
           modelMap.addAttribute("items", itemsToBuy);
            return "purchasesuccess";
        } else {
            return "cart";
        }
    }

    @RequestMapping(value = {"/user/cart"}, method = RequestMethod.GET)
    public String viewUserCart(ModelMap modelMap) {

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);

        modelMap.addAttribute("cartItems", cartService.getItemsInCart());
        modelMap.addAttribute("itemsValue", cartService.getItemsValue());
        modelMap.addAttribute("accountBalance", currentUser.getBalance());

        return "cart";
    }

    @RequestMapping(value = {"/removefromcart/{id}"})
    public String removeFromCart(ModelMap modelMap, @PathVariable Integer id) {

        String currentUserName = principalService.getPrincipal();
        User currentUser = userService.findBySSO(currentUserName);

        cartService.removeItem(id);

        modelMap.addAttribute("cartItems", cartService.getItemsInCart());
        modelMap.addAttribute("itemsValue", cartService.getItemsValue());
        modelMap.addAttribute("accountBalance", currentUser.getBalance());

        return "cart";
    }
}