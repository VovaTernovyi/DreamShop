package com.vova.webshop.dao.itemdao;

import com.vova.webshop.dao.AbstractDao;
import com.vova.webshop.model.itemmodel.Record;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("recordDao")
public class RecordDaoImpl extends AbstractDao<Integer, Record> implements RecordDao {
    @Override
    public Record findById(int id) {
        return getByKey(id);
    }


    @Override
    public void save(Record record) {
        persist(record);
    }

    @Override
    public List<Record> findAllInstances() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("item_Name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Record>) criteria.list();
    }

    @Override
    public List<Record> findInstancesByBuyerId(Integer sellerId) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("BUYER_ID", sellerId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Record>) criteria.list();
    }
}