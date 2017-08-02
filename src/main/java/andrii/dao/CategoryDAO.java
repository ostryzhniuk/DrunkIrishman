package andrii.dao;

import andrii.entities.Category;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryDAO extends GenericDAO<Category> {

    @Override
    public void save(Category category) {
        getSession().save(category);
    }

    @Override
    public List<Category> getObjects() {
        return getSession().createQuery("from Category " +
                "where isActive = true").list();
    }

    public Category getCategory(String categoryName) {
        Query<Category> query = getSession().createQuery("from Category " +
                "where name = :categoryName and " +
                "isActive = true");

        query.setParameter("categoryName", categoryName);
        return query.getSingleResult();
    }

    public Category getCategory(Integer categoryId) {
        Query<Category> query = getSession().createQuery("from Category " +
                "where id = :categoryId and " +
                "isActive = true");

        query.setParameter("categoryId", categoryId);
        return query.getSingleResult();
    }

    @Override
    public void update(Category category) {
        getSession().update(category);
    }

    @Override
    public void delete(Category category) {
        getSession().delete(category);
    }

    public void deactivate(Integer categoryId) {
        Query query = getSession().createQuery("update Category " +
                "set isActive = false " +
                "where id = :id");

        query.setParameter("id", categoryId);
        query.executeUpdate();
    }

}
