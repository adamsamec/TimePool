package cz.timepool.service;

import cz.timepool.dto.CommentDto;
import cz.timepool.dto.UserDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas L.
 */
@PreAuthorize("hasRole('ADMIN')")
@Transactional
public interface UsersServiceIface {

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId);

    @PreAuthorize("permitAll")
    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email);

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers();

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOrderedByName();

    @PreAuthorize("permitAll")
    public Long addUser(String name, String surname, String email, String password, String description);

    // TODO: pouze ADMIN 
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void editUser(UserDto userDto);
    
    // TODO: vyuzit SecurityContextHolder anebo spis @PreAuthorize u editUser(UserDto userDto);
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    //public void editLoggedUser()

    // TODO: viz editUser()
    public void deleteUser(Long userId);

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(Long userId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Long addCommentToEvent(String text, Long authorId, Long eventId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void editCommentById(String text, Long commentId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteCommentById(Long commentId);

}
