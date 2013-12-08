
package cz.timepool.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component("registration")
public class RegistrationBB {
    @Autowired
    UsersService usersService;
    UserDto user;

    public RegistrationBB() {
    user = new UserDto(Long.MIN_VALUE, null, null, null, null, null, UserRole.REGISTERED, null, null, null, null, null, null);
    }

    public UserDto getUser() {
//	if(user == null){
//	    user = new UserDto(null, null, null, null, null, null, null, null, null, null, null, null, null);
//	}
	return user;
    }

    public void setUser(UserDto user) {
	this.user = user;
    }
    
    public String register(String outcome){
	System.out.println("MEJL "+user.getEmail());
	usersService.addUser(outcome, outcome, outcome, outcome, outcome);
	return outcome;
    }
}