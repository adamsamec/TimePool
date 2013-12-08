
package cz.timepool.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
//@FacesValidator("cz.timepool.validators.NoValueValidator")
@Component
public class NoValueValidator implements Validator {

    public NoValueValidator() {
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component,
	    Object value) throws ValidatorException {
	String val = (String)value;
	System.out.println("hodnota : "+val);
	if (val.equals("")) {

	    FacesMessage msg =
		    new FacesMessage("You have to fill this field.",
		    "No value.");
	    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    throw new ValidatorException(msg);

	}

    }
}
