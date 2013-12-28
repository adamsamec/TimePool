package cz.timepool.validators;

import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersServiceIface;
import org.apache.log4j.Logger;
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
    
    private static final Logger log = Logger.getLogger(UnregisteredValidator.class);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("Validuju \"" + value +  "\" pomoci " + this.getClass().getSimpleName());
        String email = (String) value;
        UserDto userDto = null;
        try {
            userDto = usersService.getUserByEmail(email);
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        if (userDto != null) {
            FacesMessage msg = new FacesMessage("Unregistered e-mail validation failed.", "E-mail already used.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
