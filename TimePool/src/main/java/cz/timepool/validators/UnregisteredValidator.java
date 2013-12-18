package cz.timepool.validators;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UnregisteredValidator implements Validator {

    @Autowired
    private UsersServiceIface usersService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("jsem v UnregisteredValidator");
        if (value == null || value.equals("")) {
            return;
        }
        String email = (String) value;
        UserDto userDto = null;
        try {
            userDto = usersService.getUserByEmail(email);
        } catch (Exception ex) {
        }
        if (userDto != null) {
            FacesMessage msg = new FacesMessage("Unregistered e-mail validation failed.", "Choose another e-mail.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
