package com.vova.webshop.service.itemservice;

import com.vova.webshop.model.itemmodel.Record;
import com.vova.webshop.model.usermodel.User;

import java.util.List;

public interface RecordService {
    Record findById(int id);

    void saveRecord(Record item);

    List<Record> findAllRecords();

    List<Record> findRecordsByUser(User user);

}