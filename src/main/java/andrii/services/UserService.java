package andrii.services;

import andrii.dao.UserDAO;
import andrii.dao.UserRoleDAO;
import andrii.dto.UserDTO;
import andrii.entities.User;
import andrii.entities.UserRole;
import andrii.security.UserRoleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int save(UserDTO userDTO) {
        return save(userDTO.convertToEntity());
    }

   /**
    * @return -1 if user already exist,
    *          0 if exception was thrown,
    *          1 record saved successfully.
    */
    public int save(User user) {
        if (isExist(user)) {
            return -1;
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        try {
            userDAO.save(user);
            try {
                userRoleDAO.save(createUserRole(user));
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            LOGGER.warn("user saving failed", e);
            return 0;
        }

        return 1;
    }

    private UserRole createUserRole(User user){
        UserRoleBuilder userRoleBuilder = new UserRoleBuilder();
        userRoleBuilder.setUser(user);
        userRoleBuilder.setDefaultAuthority();
        return userRoleBuilder.getUserRole();
    }

    private boolean isExist(User user){
        return userDAO.getUserByEmail(user.getEmail()).isEmpty() ? false : true;
    }


}
