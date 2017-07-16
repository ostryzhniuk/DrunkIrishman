package andrii.dao;

import andrii.entities.Category;

import java.util.List;

public class CategoryDao extends GenericDao<Category> {

    @Override
    public void save(Category value) {

    }

    @Override
    public List<Category> getObjects() {
        return getSession().createQuery("from Category").list();
    }

    @Override
    public void update(Category value) {

    }

    @Override
    public void delete(Category value) {

    }
}
