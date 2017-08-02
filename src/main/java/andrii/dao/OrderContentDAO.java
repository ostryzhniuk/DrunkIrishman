package andrii.dao;

import andrii.entities.Order;
import andrii.entities.OrderContent;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderContentDAO extends GenericDAO<OrderContent> {

    @Override
    public void save(OrderContent orderContent) {
        getSession().save(orderContent);
    }

    @Override
    public List<OrderContent> getObjects() {
        return getSession().createQuery("from OrderContent").list();
    }

    public List<OrderContent> getObjects(Integer orderId) {
        Query<OrderContent> query = getSession().createQuery("from OrderContent " +
                "where order.id = :id");

        query.setParameter("id", orderId);
        return query.list();
    }

    @Override
    public void update(OrderContent orderContent) {
        getSession().update(orderContent);
    }

    @Override
    public void delete(OrderContent orderContent) {
        getSession().delete(orderContent);
    }
}
