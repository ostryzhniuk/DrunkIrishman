package andrii.services;

import andrii.dao.UserDao;
import andrii.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getFirstUser() {
        return userDao.getObjects().get(0);
    }
}
