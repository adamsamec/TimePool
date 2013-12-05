package cz.timepool.service;

import cz.timepool.bo.User;
import cz.timepool.dto.UserDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas Lowinger
 */
@Component
public class UserServiceImpl extends AbstractDataAccessService implements UserService {

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.timepoolDao.getAll(User.class);
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (User u : users) {
			// TODO: Vytvorit konstruktor prijimaci Entitu a z ni inicializovat DTO-
			userDtos.add(new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms())));
		}
		return userDtos;
	}

	@Override
	public List<UserDto> getAllUsersOrderedByName() {
		List<User> users = this.timepoolDao.getAllOrdered("name", User.class);
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for (User u : users) {
			userDtos.add(new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms())));
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
		return this.timepoolDao.save(user).getId();
	}

	@Override
	public void deleteUser(Long userId) {
		timepoolDao.removeById(userId, User.class);
	}

	@Override
	public UserDto getUserById(Long id) {
		User u = this.timepoolDao.getSingleByProperty("id", id, User.class);
		return new UserDto(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.getPassword(), u.getDescription(), u.getCreationDate(), DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()), DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()), DtoTransformerHelper.getIdentifiers(u.getAuthoredComments()), DtoTransformerHelper.getIdentifiers(u.getAcceptedTerms()));
	}

}
