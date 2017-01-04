package com.vova.webshop.service.itemservice;

import com.vova.webshop.model.itemmodel.Item;

import java.util.List;

public interface ItemService {

    Item findById(int id);

    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItemById(Integer id);

    List<Item> findAllItems();

    List<Item> getRandomItems(int amount);

    List<Item> searchItemsByName(String expression);

    List<Item> searchItemsByNameAndDescription(String expression);
}