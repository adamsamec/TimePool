package cz.timepool.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
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
@Component
public class Session implements Serializable {

    @Autowired
    private UsersService usersService;

    private UserDto user;
    boolean isAdmin;

    public String getCurrentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // TODO: proc je to umozneno?
    public void deleteUser(Long id) {
        usersService.deleteUser(id);
        //FacesUtil.addMessage("User was sucessfully deleted");
    }

    // TODO: Pri prvnim volani ulozit do this.user a zaroven nastavit this.isAdmin
    public UserDto getUser() {
        return usersService.getUserByEmail(getCurrentEmail());
    }

    // TODO: proc je to umozneno?
    public void setUser(UserDto user) {
        this.user = user;
    }

    // TODO: pouze vratit jiz nastaveny this.isAdmin
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

    // TODO: proc je to umozneno?
//    public void setIsAdmin(boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }

}
