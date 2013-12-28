package cz.timepool.pres.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.helper.FacesHelper;
import cz.timepool.service.UsersServiceIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UserSingle {

    @Autowired
    UsersServiceIface usersService;

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
        Long userId = Long.valueOf(FacesHelper.getRequestParameter("user_id"));
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
            user.setUserRole(UserRole.USER);
        }
        usersService.editUser(user);
        return outcome;
    }

    public void deleteUser() {
        Long userId = Long.valueOf(FacesHelper.getRequestParameter("delete_user_id"));
        usersService.deleteUser(userId);
    }

}
