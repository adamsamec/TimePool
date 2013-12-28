package cz.timepool.pres.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import java.io.Serializable;
import java.util.Collection;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private UserDto user;

//     private boolean isAdmin;

    public String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // TODO: Pri prvnim volani ulozit do this.user a zaroven nastavit this.isAdmin
    // TODO: ma smysl to cachovat do this.user nebo to zajisti entityManager?
    public UserDto getUser() {
        setUser(usersService.getUserByEmail(getUserEmail()));
        return user;
    }

    // TODO: proc je to umozneno?
    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isIsAdmin() {
        return getUser().getUserRole() == UserRole.ADMIN;
    }

}
