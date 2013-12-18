package cz.timepool.service;

import cz.timepool.dto.CommentDto;
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

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId);

    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email);

    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers();

    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOrderedByName();

    public Long addUser(String name, String surname, String email, String password, String description);

    @Secured({"ROLE_ADMIN"})
    public void editUser(UserDto userDto);

    @Secured({"ROLE_ADMIN"})
    public void deleteUser(Long userId);

    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(Long userId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Long addCommentToEvent(String text, Long authorId, Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void editCommentById(String text, Long commentId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteCommentById(Long commentId);

}
