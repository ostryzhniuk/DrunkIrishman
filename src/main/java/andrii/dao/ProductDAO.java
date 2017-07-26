package andrii.dao;

import andrii.entities.Product;
import andrii.entities.Stock;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDAO extends GenericDAO<Product> {

    @Override
    public void save(Product product) {

    }

    @Override
    public List<Product> getObjects() {
        return getSession().createQuery("from Product")
                .list();
    }

    public List<Product> getProductsByCategory(String categoryName) {

        Query query = getSession().createQuery("select p " +
                "from Product as p, Category as c " +
                "where c.name = :categoryName and " +
                "c.id = p.category.id");

        query.setParameter("categoryName", categoryName);

        return query.list();
    }

    public Product getProductById(Integer productId) {

        Query<Product> query = getSession().createQuery("from Product " +
                "where id = :productId");

        query.setParameter("productId", productId);

        return query.getSingleResult();
    }

    public List<Product> getProductByName(String productName) {

        Query<Product> query = getSession().createQuery("from Product " +
                "where lower(name) like lower(:productName)");

        query.setParameter("productName", "%"+productName+"%");

        return query.list();
    }

    public List<Product> getProductsWithStatus(Integer productId, Stock.Status status) {
        Query query = getSession().createQuery("select p " +
                "from Product as p, Stock as s " +
                "where p.id = :productId and " +
                "p.id = s.product.id and " +
                "s.status = :status");

        query.setParameter("productId", productId);
        query.setParameter("status", status);

        return query.list();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(Product product) {

    }
}
