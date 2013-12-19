package cz.timepool.pres.bb;

import cz.timepool.helper.FacesHelper;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@RequestScoped
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
