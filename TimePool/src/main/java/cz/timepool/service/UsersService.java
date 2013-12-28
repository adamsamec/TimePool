package cz.timepool.service;

import cz.timepool.bo.Comment;
import cz.timepool.bo.Event;
import cz.timepool.bo.User;
import cz.timepool.bo.UserRole;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.UserDto;
import cz.timepool.helper.AuthenticationHelper;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UsersService extends TimepoolService implements UsersServiceIface {
    
    private static final Logger log = Logger.getLogger(UsersService.class);

    @Override
    public UserDto getUserById(Long userId) {
        User u = this.timepoolDao.getSingleByProperty("id", userId, User.class);
        return new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(), u.getUserRole(), u.getAuthKey(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations()));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user;
        try {
            user = this.timepoolDao.getSingleByProperty("email", email, User.class);
        } catch (NoResultException ex) {
            return null;
        }
        return new UserDto(user.getId(), user.getEmail(), user.getName(), user.getSurname(), user.getPassword(), user.getDescription(), user.getUserRole(), user.getAuthKey(), user.getCreationDate(), DtoTransformerHelper.getIdentifiers(user.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(user.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(user.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(user.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(user.getEventInvitations()));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.timepoolDao.getAll(User.class);
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User u : users) {
            // TODO: Vytvorit konstruktor prijimaci Entitu a z ni inicializovat DTO-
            userDtos.add(new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(), u.getUserRole(), u.getAuthKey(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations())));
        }
        return userDtos;
    }

    @Override
    public List<UserDto> getAllUsersOrderedByName() {
        List<User> users = this.timepoolDao.getAllOrdered("name", User.class);
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User u : users) {
            userDtos.add(new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(), u.getUserRole(), u.getAuthKey(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()), DtoTransformerHelper.getIdentifiers(u.getEventInvitations())));
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
        user.setPassword(AuthenticationHelper.userPasswordHash(password, email));
        user.setDescription(description);
        user.setUserRole(UserRole.USER);
        return this.timepoolDao.save(user).getId();
    }

    @Override
    public void editUser(UserDto userDto) {
        User user = timepoolDao.loadById(userDto.getId(), User.class);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        if (!userDto.getPassword().isEmpty()) {
            user.setPassword(AuthenticationHelper.userPasswordHash(userDto.getPassword(), userDto.getEmail()));
        }
        user.setUserRole(userDto.getUserRole());
        timepoolDao.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        timepoolDao.removeById(userId, User.class);
    }

    @Override
    public List<CommentDto> getCommentsByUser(Long userId) {
        List<Comment> boList = this.timepoolDao.loadById(userId, User.class).getAuthoredComments();
        List<CommentDto> dtoList = new ArrayList<CommentDto>();
        for (Comment comment : boList) {
            log.info("Retrieving the comment: " + comment);
            dtoList.add(new CommentDto(comment.getId(), comment.getAuthor().getId(), comment.getEvent().getId(), comment.getText(), comment.getCreationDate()));
        }
        return dtoList;
    }

    @Override
    public Long addCommentToEvent(String text, Long authorId, Long eventId) {
        Event event = this.timepoolDao.loadById(eventId, Event.class);
        User a = this.timepoolDao.loadById(authorId, User.class);
        Comment c = new Comment();
        c.setAuthor(a);
        c.setText(text);
        c.setCreationDate(new Date());
        c.setEvent(event);
        return this.timepoolDao.save(c).getId();
    }

    @Override
    public void editCommentById(String text, Long commentId) {
        Comment c = this.timepoolDao.loadById(commentId, Comment.class);
        c.setText(text);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        log.info("Deleting the comment: " + commentId);
        this.timepoolDao.removeById(commentId, Comment.class);
    }

}
