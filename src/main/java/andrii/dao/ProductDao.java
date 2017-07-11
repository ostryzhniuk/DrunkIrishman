package andrii.dao;

import andrii.entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDao extends GenericDao<Product> {

    @Override
    public void save(Product product) {

    }

    @Override
    public List<Product> getObjects() {
        return getSession().createQuery("from Product")
                .list();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
