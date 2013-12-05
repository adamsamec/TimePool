package cz.timepool.service;

import cz.timepool.dto.CommentDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
@Transactional
public interface CommentService {

    @Transactional(readOnly = true)
    public List<CommentDto> getAllByTerm(Long idTerm);

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByUser(Long idUser);

    public Long addCommentToTerm(String text, Long idAuthor, Long idTerm);

    public void editCommentById(String text, Long id);

    public void deleteCommentById(Long id);

}
