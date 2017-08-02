package andrii.services;

import andrii.dao.UserDAO;
import andrii.dao.UserRoleDAO;
import andrii.dto.UserDTO;
import andrii.dto.UserSignUpDTO;
import andrii.entities.User;
import andrii.entities.UserRole;
import andrii.entities.UserRoleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void save(UserSignUpDTO userDTO) {
        User user = userDTO.convertToEntity();

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userDAO.save(user);
        userRoleDAO.save(createUserRole(user));
    }

    private UserRole createUserRole(User user){
        UserRoleBuilder userRoleBuilder = new UserRoleBuilder();
        userRoleBuilder.setUser(user);
        userRoleBuilder.setDefaultAuthority();
        return userRoleBuilder.getUserRole();
    }

    @Transactional
    public UserDTO getUserDTOByEmail(String email){
        return UserDTO.convertToDTO(userDAO.getUserByEmail(email));
    }

    @Transactional
    public User getUserByEmail(String email){
        return userDAO.getUserByEmail(email);
    }

    public org.springframework.security.core.userdetails.User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return new org.springframework.security.core.userdetails.User(authentication.getName(),
                    "", authentication.getAuthorities());
        }
        return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    }

}
