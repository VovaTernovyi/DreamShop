package com.vova.webshop.dao.itemdao;

import com.vova.webshop.model.itemmodel.Record;

import java.util.List;

public interface RecordDao extends RecordItemDao<Record> {
    List<Record> findInstancesByBuyerId(Integer sellerId);
}