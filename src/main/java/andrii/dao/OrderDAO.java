package andrii.dao;

import andrii.entities.Order;
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
}
