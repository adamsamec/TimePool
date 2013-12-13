package cz.timepool.service;

import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.UserDto;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas L.
 */
@Transactional
public interface UsersService {
    
    public Long addUser(String name, String surname, String email, String password, String description);
    
    @Secured({"ROLE_ADMIN"})
    public void deleteUser(Long userId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id);
    
    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email);

    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers();

    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOrderedByName();
    
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(Long idUser);
   
    public Long addCommentToEvent(String text, Long idAuthor, Long idEvent);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void editCommentById(String text, Long id);

    @Secured({"ROLE_ADMIN"})
    public void deleteCommentById(Long id);
    
    @Secured({"ROLE_ADMIN"})
    public void editUser(UserDto user);
    
}
