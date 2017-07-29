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
        return getSession().createQuery("from Category").list();
    }

    public Category getCategoryByName(String categoryName) {
        Query<Category> query = getSession().createQuery("from Category " +
                "where name = :categoryName");

        query.setParameter("categoryName", categoryName);
        return query.getSingleResult();
    }

    @Override
    public void update(Category category) {
        Query query = getSession().createQuery("update Category " +
                "set name = :name " +
                "where id = :id");
        query.setParameter("name", category.getName());
        query.setParameter("id", category.getId());
        query.executeUpdate();
    }

    @Override
    public void delete(Category category) {

    }
}
