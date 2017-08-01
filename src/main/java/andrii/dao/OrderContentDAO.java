package andrii.dao;

import andrii.entities.OrderContent;
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

    @Override
    public void update(OrderContent orderContent) {
        getSession().update(orderContent);
    }

    @Override
    public void delete(OrderContent orderContent) {
        getSession().delete(orderContent);
    }
}
