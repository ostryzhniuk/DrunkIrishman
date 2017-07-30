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

    public Category getCategoryByName(String categoryName) {
        Query<Category> query = getSession().createQuery("from Category " +
                "where name = :categoryName");

        query.setParameter("categoryName", categoryName);
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

    public void deleteById(Integer categoryId) {
        Query query = getSession().createQuery("delete Category " +
                "where id = :categoryId");
        query.setParameter("categoryId", categoryId);
        query.executeUpdate();
    }
}
