
package cz.timepool.bb;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component("loggedUser")
public class LoggedUserBean implements Serializable{
    @Autowired
    private UsersService usersService;
    
    private UserDto user;
    
    public String getCurrentEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    
    public void deleteUser(Long id){
        usersService.deleteUser(id);
        //FacesUtil.addMessage("User was sucessfully deleted");
    }

    public UserDto getUser() {
	return usersService.getUserByEmail(getCurrentEmail());
    }

    public void setUser(UserDto user) {
	this.user = user;
    }

    public UsersService getUserService() {
        return usersService;
    }

    public void setUsersService(UsersService userService) {
        this.usersService = userService;
    }
    
    
}
