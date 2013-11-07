
package cz.timepool.service;

import cz.timepool.dto.UserDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
public interface UserService {
    //TODO: 
    public Long addUser(String name, String surname, String email, String pass, String description);

    public void deleteUser(Long userId);

    @Transactional(readOnly=true)
    public UserDto getUserById(Long id);

    @Transactional(readOnly=true)
    public List<UserDto> getAllUsers();
}