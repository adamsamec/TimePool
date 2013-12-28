package cz.timepool.pres.bb;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Adam Samec <samecada@fel.cvut.cz>
 * @link   fel.cvut.cz
 */
@Component
public class UserList {

    @Autowired
    private UsersServiceIface usersService;

    public List<UserDto> getAllUsers() {
        return usersService.getAllUsers();
    }

}
