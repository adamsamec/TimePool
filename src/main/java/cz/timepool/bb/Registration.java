package cz.timepool.bb;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component("registrationBB")
public class Registration {

    @Autowired
    UsersService usersService;

    UserDto user;

    public UserDto getUser() {
        if (user == null) {
            user = new UserDto(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        }
        return user;
    }

    // TODO: Tento zbytecny setter tu musi byt, aby se dodrzela specifikace Bean?
    public void setUser(UserDto user) {
        this.user = user;
    }

    public String register(String outcome) {
        usersService.addUser(user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getDescription());
        return outcome;
    }

}
