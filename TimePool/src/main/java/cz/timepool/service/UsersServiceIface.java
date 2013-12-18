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
@Secured({"ROLE_ADMIN"})
@Transactional
public interface UsersServiceIface {

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId);

    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email);

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers();

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOrderedByName();

    public Long addUser(String name, String surname, String email, String password, String description);

    public void editUser(UserDto userDto);

    public void deleteUser(Long userId);

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(Long userId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Long addCommentToEvent(String text, Long authorId, Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void editCommentById(String text, Long commentId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteCommentById(Long commentId);

}
