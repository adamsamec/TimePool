package cz.timepool.service;

import cz.timepool.dto.CommentDto;
import cz.timepool.dto.UserDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas L.
 */
@Transactional
public interface UsersService {

    public Long addUser(String name, String surname, String email, String password, String description);

    public void deleteUser(Long userId);

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id);

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers();

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersOrderedByName();

    @Transactional(readOnly = true)
    public List<CommentDto> getAllByTerm(Long idTerm);

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(Long idUser);

    public Long addCommentToTerm(String text, Long idAuthor, Long idTerm);

    public void editCommentById(String text, Long id);

    public void deleteCommentById(Long id);
}
