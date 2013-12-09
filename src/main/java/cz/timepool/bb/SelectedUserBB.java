
package cz.timepool.bb;

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
public class SelectedUserBB {
    @Autowired
    UsersService usersService;
    
    UserDto user;
    
    public String setUserById(String outcome){
	Long userId = Long.valueOf(FacesUtil.getRequestParameter("userid"));
	this.user = usersService.getUserById(userId);
	return outcome;
    }
    
    public void deleteUser(){
	Long userId = Long.valueOf(FacesUtil.getRequestParameter("deleteuserid"));
	usersService.deleteUser(userId);
    }
}
