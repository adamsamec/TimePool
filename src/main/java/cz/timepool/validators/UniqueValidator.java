
package cz.timepool.validators;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.UserDto;
import cz.timepool.service.UsersService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component("uniqueValidator")
public class UniqueValidator implements Validator{
    @Autowired
    UsersService usersService;
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
	if(o == null || o.equals(""))return;
	String email = (String)o;
	System.out.println("jsem v unique validatoru");
	UserDto dto = null;
	try{
	dto= usersService.getUserByEmail(email);
	}
	catch(Exception ex){
	    dto = new UserDto(Long.MIN_VALUE, email, email, email, email, email, UserRole.REGISTERED, null, null, null, null, null, null);
	}
	if(dto!=null){
	    	    FacesMessage msg =
		    new FacesMessage("Unique validation failed",
		    "This field has to be unique.");
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    throw new ValidatorException(msg);
	}
    }

}
