package cz.timepool.pres.bb;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class LoginSession implements Serializable {

    @Autowired
    private UsersServiceIface usersService;

    private UserDto user;

    private boolean isAdmin;

    public String getUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // TODO: proc je to umozneno?
//    public void deleteUser(Long id) {
//        usersService.deleteUser(id);
//        //FacesUtil.addMessage("User was sucessfully deleted");
//    }
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

    // TODO: pouze vratit jiz nastaveny this.isAdmin?
    public boolean isIsAdmin() {
        Collection<? extends GrantedAuthority> auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return auths.contains(new SimpleGrantedAuthority("ROLE_ADMIN")); // TODO: ok?
//        if (getUser().getUserRole() == UserRole.ADMIN) {
//            isAdmin = true;
//        } else {
//            isAdmin = false;
//        }
//        return isAdmin;
    }

}
