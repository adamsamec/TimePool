
package cz.timepool.service;

import cz.timepool.dto.CommentDto;
import cz.timepool.dto.TermDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public interface CommentService{
    // TODO: 
    public List<CommentDto> getAllByTerm(Long idTerm);
    public void addCommentToTerm(String text, Long idAuthor, Long idTerm);
    public void editCommentById(String text, Long id);
    public void deleteCommentById(Long id);
}
