package cz.timepool.validators;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class UnregisteredEmailValidator implements Validator {
    
    @Autowired
    private ApplicationContext context;

    private ArrayList<Validator> validators;
    
    
//    public UnregisteredEmailValidator(Validator validator1, Validator validator2) {
//        validators = new ArrayList<Validator>();
//        validators.add(new UnregisteredValidator());
//        validators.add(new EmailValidator());
//    }
    
    @PostConstruct
    public void init() {
        validators = new ArrayList<Validator>();
        validators.add(context.getBean(UnregisteredValidator.class));
        validators.add(context.getBean(EmailValidator.class));
    }

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
