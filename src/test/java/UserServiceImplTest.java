
import cz.timepool.dto.UserDto;
import cz.timepool.service.UserService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Lukas Lowinger
 */
public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    public UserServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrieveUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();
        
        Long id = userService.addUser(name,surname,email,pass,desctiption);
        UserDto userDto = userService.getUserById(id);
        
        assertEquals(name, userDto.getName());
        assertEquals(email, userDto.getEmail());
        
    }
    @Test
    public void testAddAndRemoveUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();
        
        Long id = userService.addUser(name,surname,email,pass,desctiption);
        assertEquals(1, userService.getAllUsers().size());
        userService.deleteUser(id);
        assertEquals(0, userService.getAllUsers().size());        
    }      

    private long addUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();

        return userService.addUser(name,surname,email,pass,desctiption);
    }
}
