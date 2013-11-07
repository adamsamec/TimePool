
package cz.timepool.service;

import cz.timepool.bo.Comment;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.TermDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
class CommentServiceImpl extends AbstractDataAccessService implements CommentService{

    @Transactional(readOnly=true)
    @Override
    public List<CommentDto> getAllByTerm(Long idTerm) {
        List<Comment> boList = genericDao.getByProperty("id", idTerm, Comment.class);
        List<CommentDto> dtoList = new ArrayList<CommentDto>();
        for (Comment comment : boList) {
           // dtoList.add(new CommentDto(comment.getId(), comment.getAuthor().getId(),comment.getTerm().getId() , comment.getText(), comment.getCreationDate()));
        }
        return dtoList;
    }

    @Override
    public void addCommentToTerm(String text, Long idAuthor, Long idTerm) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editCommentById(String text, Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteCommentById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
