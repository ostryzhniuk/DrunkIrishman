package andrii.dao;

import andrii.entities.Product;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDAO extends GenericDAO<Product> {

    @Override
    public void save(Product product) {
        getSession().save(product);
    }

    @Override
    public List<Product> getObjects() {
        return getSession().createQuery("select p " +
                "from Product as p, Category as c " +
                "where p.isActive = true and " +
                "p.category.id = c.id and " +
                "c.isActive = true")
                .list();
    }

    public List<Product> getProductsByCategory(String categoryName) {

        Query query = getSession().createQuery("select p " +
                "from Product as p, Category as c " +
                "where c.name = :categoryName and " +
                "c.isActive = true and " +
                "c.id = p.category.id and " +
                "p.isActive = true");

        query.setParameter("categoryName", categoryName);

        return query.list();
    }

    public Product getProductById(Integer productId) {

        Query<Product> query = getSession().createQuery("select p " +
                "from Product as p, Category as c " +
                "where p.id = :productId and " +
                "p.isActive = true and " +
                "p.category.id = c.id and " +
                "c.isActive = true");

        query.setParameter("productId", productId);

        return query.getSingleResult();
    }

    public List<Product> getProductByName(String productName) {

        Query<Product> query = getSession().createQuery("select p " +
                "from Product as p, Category as c " +
                "where p.isActive = true and " +
                "lower(p.name) like lower(:productName) and " +
                "p.category.id = c.id and " +
                "c.isActive = true");

        query.setParameter("productName", "%"+productName+"%");

        return query.list();
    }

    @Override
    public void update(Product product) {
        getSession().update(product);
    }

    @Override
    public void delete(Product product) {
        getSession().delete(product);
    }

    public void deleteByCategory(Integer categoryId) {
        Query query = getSession().createQuery("delete Product " +
                "where category.id = :categoryId");
        query.setParameter("categoryId", categoryId);
        query.executeUpdate();
    }
}
