
package cz.timepool.service;

import cz.timepool.bo.Comment;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.TermDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
@Component
class CommentServiceImpl extends AbstractDataAccessService implements CommentService{

    @Transactional(readOnly=true)
    @Override
    public List<CommentDto> getAllByTerm(Long idTerm) {
        List<Comment> boList = genericDao.getByProperty("term", genericDao.loadById(idTerm, Term.class), Comment.class);
        List<CommentDto> dtoList = new ArrayList<CommentDto>();
        for (Comment comment : boList) {
            dtoList.add(new CommentDto(comment.getId(), comment.getAuthor().getId(),comment.getTerm().getId() , comment.getText(), comment.getCreationDate()));
        }
        return dtoList;
    }

    @Override
    public Long addCommentToTerm(String text, Long idAuthor, Long idTerm) {
        Term term = genericDao.loadById(idTerm, Term.class);
        User a = genericDao.loadById(idAuthor, User.class);
        Comment c = new Comment();
        c.setAuthor(a);
        c.setText(text);
        c.setCreationDate(new Date());
        c.setTerm(term);
        return genericDao.saveOrUpdate(c).getId();
    }

    @Override
    public void editCommentById(String text, Long id) {
        Comment c = genericDao.loadById(id, Comment.class);
        c.setText(text);
    }

    @Override
    public void deleteCommentById(Long id) {
        genericDao.removeById(id, Comment.class);
    }

}
