package cz.timepool.validators;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class RegisteredValidator implements Validator {

    @Autowired
    private UsersServiceIface usersService;

    private static final Logger log = Logger.getLogger(UnregisteredValidator.class);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("Validuju \"" + value + "\" pomoci " + this.getClass().getSimpleName());
        String email = (String) value;
        UserDto userDto = null;
        try {
            userDto = usersService.getUserByEmail(email);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        if (userDto == null) {
            FacesMessage msg = new FacesMessage("Registered e-mail validation failed.", "User with this e-mail does not exist.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
