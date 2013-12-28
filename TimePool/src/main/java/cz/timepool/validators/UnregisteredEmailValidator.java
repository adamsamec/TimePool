package cz.timepool.validators;

import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UnregisteredEmailValidator implements Validator {

    private ArrayList<Validator> validators;

    private static final Logger log = Logger.getLogger(UnregisteredValidator.class);

    public void setUnregisteredEmailValidators(ArrayList<Validator> unregisteredEmailValidators) {
        this.validators = unregisteredEmailValidators;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("Validuju \"" + value + "\" pomoci " + this.getClass().getSimpleName());
        for (Validator validator : validators) {
            validator.validate(context, component, value);
        }
    }

}
