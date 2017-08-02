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

    public List<Order> getOrders(String userEmail) {
        Query<Order> query = getSession().createQuery("from Order " +
                "where user.email = :email");

        query.setParameter("email", userEmail);
        return query.list();
    }

    public Order getOrder(Integer orderId) {
        Query<Order> query = getSession().createQuery("from Order " +
                "where id = :id");

        query.setParameter("id", orderId);
        return query.getSingleResult();
    }

    public void updateOrderStatus(Order order) {
        Query query = getSession().createQuery("update Order " +
                "set status = :status " +
                "where id = :id");

        query.setParameter("id", order.getId());
        query.setParameter("status", order.getStatus());
        query.executeUpdate();
    }
}
