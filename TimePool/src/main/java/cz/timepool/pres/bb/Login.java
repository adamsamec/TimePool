package cz.timepool.pres.bb;

import cz.timepool.helper.FacesHelper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Scope("request")
@Component
public class Login {

    public boolean isAuthFailure() {
        String authFailureParam = FacesHelper.getRequestParameter("auth_failure");
        return ((authFailureParam != null) && authFailureParam.equals("true"));
    }

    public boolean isRegistrationSuccess() {
        String registrationSuccess = FacesHelper.getRequestParameter("registration_success");
        return ((registrationSuccess != null) && registrationSuccess.equals("true"));
    }

}
