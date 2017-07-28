package andrii.dao;

import andrii.entities.Category;
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

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }
}
