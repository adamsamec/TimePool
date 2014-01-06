package cz.timepool.pres.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Scope("session")
@Component
public class LoginSession implements Serializable {

    private UserDto user;

    @Autowired
    private UsersServiceIface usersService;

    public String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserDto getUser() {
        if (user == null) {
            user = usersService.getUserByEmail(getUserEmail());
        }
        return user;
    }

    public boolean isIsAdmin() {
        return getUser().getUserRole() == UserRole.ADMIN;
    }

}
