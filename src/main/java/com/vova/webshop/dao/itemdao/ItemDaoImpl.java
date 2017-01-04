package com.vova.webshop.dao.itemdao;

import com.vova.webshop.dao.AbstractDao;
import com.vova.webshop.model.itemmodel.Item;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDao")
public class ItemDaoImpl extends AbstractDao<Integer, Item> implements ItemDao {

    @Override
    public Item findById(int id) {
        return getByKey(id);
    }


    @Override
    public void save(Item item) {
        persist(item);
    }

    @Override
    public void deleteById(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Item item = (Item) crit.uniqueResult();
        delete(item);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> findAllInstances() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("itemName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Item>) criteria.list();
    }
}