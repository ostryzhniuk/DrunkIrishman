package andrii.security;

import andrii.entities.User;
import andrii.entities.UserRole;

/**
 * Created by Andriy on 07/15/2017.
 */
public class UserRoleBuilder {

    private UserRole userRole;

    public UserRoleBuilder() {
        this.userRole = new UserRole();
    }

    public void setDefaultAuthority(){
        userRole.setAuthority("user");
    }

    public void setAuthority(String authority) {
        userRole.setAuthority(authority);
    }

    public void setUser(User user){
        userRole.setUser(user);
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
