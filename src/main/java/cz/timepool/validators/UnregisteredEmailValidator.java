package cz.timepool.validators;

import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UnregisteredEmailValidator implements Validator {

    private ArrayList<Validator> unregisteredEmailValidators;

    public void setUnregisteredEmailValidators(ArrayList<Validator> unregisteredEmailValidators) {
        this.unregisteredEmailValidators = unregisteredEmailValidators;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object o) throws ValidatorException {
        System.out.println("jsem v UnregisteredEmailValidator");
        for (Validator validator : unregisteredEmailValidators) {
            System.out.println("validuju " + validator.toString());
            validator.validate(context, component, o);
        }
    }

}
