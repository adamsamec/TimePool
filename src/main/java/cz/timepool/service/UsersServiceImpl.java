package cz.timepool.service;

import cz.timepool.bo.Comment;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.bo.UserRole;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.UserDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas L.
 */
@Component
public class UsersServiceImpl extends AbstractDataAccessService implements UsersService {

    @Override
    public List<UserDto> getAllUsers() {
	List<User> users = this.timepoolDao.getAll(User.class);
	List<UserDto> userDtos = new ArrayList<UserDto>();
	for (User u : users) {
	    // TODO: Vytvorit konstruktor prijimaci Entitu a z ni inicializovat DTO-
	    userDtos.add(new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(),u.getUserRole(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations())));
	}
	    return userDtos;
    }

    @Override
    public List<UserDto> getAllUsersOrderedByName() {
	List<User> users = this.timepoolDao.getAllOrdered("name", User.class);
	List<UserDto> userDtos = new ArrayList<UserDto>();
	for (User u : users) {
	    userDtos.add(new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(),u.getUserRole(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations())));
	}
	return userDtos;
    }

    @Override
    // TODO: predavat DTO
    public Long addUser(String name, String surname, String email, String password, String description) {
	User user = new User();
	user.setCreationDate(new Date());
	user.setName(name);
	user.setSurname(surname);
	user.setEmail(email);
	user.setPassword(password);
	user.setDescription(description);
	user.setUserRole(UserRole.REGISTERED);
	return this.timepoolDao.save(user).getId();
    }

    @Override
    public void deleteUser(Long userId) {
	timepoolDao.removeById(userId, User.class);
    }

    @Override
    public UserDto getUserById(Long id) {
	User u = this.timepoolDao.getSingleByProperty("id", id, User.class);
	return new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(),u.getUserRole(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations()));
    }

    @Override
    public List<CommentDto> getAllByTerm(Long idTerm) {
	Term term = this.timepoolDao.loadById(idTerm, Term.class);
	List<Comment> boList = term.getComments();
	List<CommentDto> dtoList = new ArrayList<CommentDto>();
	for (Comment comment : boList) {
	    dtoList.add(new CommentDto(comment.getId(), comment.getAuthor().getId(), comment.getTerm().getId(), comment.getText(), comment.getCreationDate()));
	}
	return dtoList;
    }

    @Override
    public Long addCommentToTerm(String text, Long idAuthor, Long idTerm) {
	Term term = this.timepoolDao.loadById(idTerm, Term.class);
	User a = this.timepoolDao.loadById(idAuthor, User.class);
	Comment c = new Comment();
	c.setAuthor(a);
	c.setText(text);
	c.setCreationDate(new Date());
	c.setTerm(term);
	return this.timepoolDao.save(c).getId();
    }

    @Override
    public void editCommentById(String text, Long id) {
	Comment c = this.timepoolDao.loadById(id, Comment.class);
	c.setText(text);
    }

    @Override
    public void deleteCommentById(Long id) {
	Comment c = this.timepoolDao.loadById(id, Comment.class);
	Term term = c.getTerm();
	term.removeComment(c);
    }

    @Override
    public List<CommentDto> getCommentsByUser(Long idUser) {
	List<Comment> boList = this.timepoolDao.loadById(idUser, User.class).getAuthoredComments();
	List<CommentDto> dtoList = new ArrayList<CommentDto>();
	for (Comment comment : boList) {
	    System.out.println("pridavam : " + comment);
	    dtoList.add(new CommentDto(comment.getId(), comment.getAuthor().getId(), comment.getTerm().getId(), comment.getText(), comment.getCreationDate()));
	}
	return dtoList;
    }

    @Override
    public UserDto getUserByEmail(String email) {
	User u = this.timepoolDao.getSingleByProperty("email", email, User.class);
	return new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(),u.getUserRole(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations()));
    }

}
