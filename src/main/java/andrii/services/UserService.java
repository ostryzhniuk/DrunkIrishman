package andrii.services;

import andrii.dao.UserDao;
import andrii.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getFirstUser() {
        return userDao.getObjects().get(0);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getObjects();
    }
}
