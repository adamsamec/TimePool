
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
public class UserServiceImpl extends AbstractDataAccessService implements UserService{

 @Override
    public List<UserDto> getAllUsers() {
        List<User> users = genericDao.getAll(User.class);
        List<UserDto> userDtos = new ArrayList<UserDto>();

        for (User u : users) {
            userDtos.add(new UserDto(u.getEmail(),u.getName(),u.getSurname(),u.getPassword(),u.getDescription(),u.getCreationDate(),DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()),DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms())));
            //userDtos.add(new UserDto(u.getEmail(),u.getName(),u.getSurname(),u.getPassword(),u.getDescription(),u.getCreationDate()));
        }
        return userDtos;
    }

    @Override
    public Long addUser(String name, String surname, String email, String pass, String description) {
        User newUser = new User();
        newUser.setCreationDate(null);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setEmail(email);
        newUser.setPassword(pass);
        newUser.setDescription(description);
        
        return genericDao.saveOrUpdate(newUser).getId();
    }

    @Override
    public void deleteUser(Long userId) {
        genericDao.removeById(userId, User.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User u = genericDao.getByPropertyUnique("id", id, User.class);
        return new UserDto(u.getEmail(),u.getName(),u.getSurname(),u.getPassword(),u.getDescription(),u.getCreationDate(),DtoTransformerHelper.getIdentifiers(u.getAuthoredEvents()),DtoTransformerHelper.getIdentifiers(u.getAuthoredTerms()));
//        return new UserDto(u.getEmail(),u.getName(),u.getSurname(),u.getPassword(),u.getDescription(),u.getCreationDate());
    }
    
}
