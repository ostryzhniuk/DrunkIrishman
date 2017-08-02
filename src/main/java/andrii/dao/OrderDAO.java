package andrii.dao;

import andrii.entities.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderDAO extends GenericDAO<Order> {


    @Override
    public void save(Order order) {
        getSession().save(order);
    }

    @Override
    public List<Order> getObjects() {
        return getSession().createQuery("from Order").list();
    }

    @Override
    public void update(Order order) {
        getSession().update(order);
    }

    @Override
    public void delete(Order order) {
        getSession().delete(order);
    }

    public List<Order> getOrders(Order.Status status) {
        Query<Order> query = getSession().createQuery("from Order " +
                "where status = :status");

        query.setParameter("status", status);
        return query.list();
    }
}
