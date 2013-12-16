package cz.timepool.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.helper.FacesUtil;
import cz.timepool.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UserSingle {

    @Autowired
    UsersService usersService;

    private UserDto user;

    private boolean isAdmin;

    public UserDto getUser() {
        if (user == null) {
            user = new UserDto(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        }
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String setUserById(String outcome) {
        Long userId = Long.valueOf(FacesUtil.getRequestParameter("user_id"));
        user = usersService.getUserById(userId);
        if (user.getUserRole() == UserRole.ADMIN) {
            isAdmin = true;
        } else {
            isAdmin = false;
        }
        return outcome;
    }

    public String addUser(String outcome) {
        usersService.addUser(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getDescription());
        return outcome;
    }

    public String editUser(String outcome) {
        if (isAdmin) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.REGISTERED);
        }
        usersService.editUser(user);
        return outcome;
    }

    public void deleteUser() {
        Long userId = Long.valueOf(FacesUtil.getRequestParameter("delete_user_id"));
        usersService.deleteUser(userId);
    }

}
