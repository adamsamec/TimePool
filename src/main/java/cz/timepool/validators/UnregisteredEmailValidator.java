
package cz.timepool.validators;

import java.util.List;
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
public class CompositeValidator implements Validator{
    @Autowired
    List<Validator> validators;
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
	for (Validator validator : validators) {
	    System.out.println("validuju kompozit");
	    validator.validate(fc, uic, o);
	}
    }

}
