package cz.timepool.pres.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@SessionScoped
@Component
public class LoginSession implements Serializable {

    @Autowired
    private UsersServiceIface usersService;

    public String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // TODO: Cachovani zajisti entityManager?
    public UserDto getUser() {
        return usersService.getUserByEmail(getUserEmail());
    }

    public boolean isIsAdmin() {
        return getUser().getUserRole() == UserRole.ADMIN;
    }

}
