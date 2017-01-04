package com.vova.webshop.service.itemservice;

import com.vova.webshop.dao.itemdao.RecordDao;
import com.vova.webshop.model.itemmodel.Record;
import com.vova.webshop.model.usermodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("recordService")
@Transactional
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    @Override
    public Record findById(int id) {
        return recordDao.findById(id);
    }

    @Override
    public void saveRecord(Record item) {
        recordDao.save(item);
    }

    @Override
    public List<Record> findAllRecords() {
        return recordDao.findAllInstances();
    }

    @Override
    public List<Record> findRecordsByUser(User user) {
        List<Record> records = recordDao.findAllInstances();
        List<Record> result = records.stream()
                .filter(record -> Objects.equals(user.getId(), record.getBuyerId()))
                .collect(Collectors.toList());
        return result;
    }
}