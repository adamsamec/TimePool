
package cz.timepool.service;

import cz.timepool.bo.Comment;
import cz.timepool.dto.CommentDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
class CommentServiceImpl extends AbstractDataAccessService implements CommentService{

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> boList = genericDao.getAll(Comment.class);
        List<CommentDto> dtoList = new ArrayList<CommentDto>();
        for (Comment comment : boList) {
            //dtoList.add(new CommentDto(comment.getId(), comment.getAuthor().getId(),comment.getTerm().getId() , comment.getText(), comment.getCreationDate()));
        }
        return dtoList;
    }

}
