package com.vova.webshop.dao.itemdao;

import com.vova.webshop.model.itemmodel.Item;

public interface ItemDao extends RecordItemDao<Item> {
    void deleteById(Integer id);
}