package andrii.dao;

import andrii.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDao extends GenericDao<User> {

    @Override
    public void save(User user) {
    }

    @Override
    public List<User> getObjects() {
        return getSession().createQuery("from User")
                .list();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
