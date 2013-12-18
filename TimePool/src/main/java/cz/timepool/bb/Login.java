package cz.timepool.bb;

import cz.timepool.helper.FacesUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Lukas L.
 */
@ManagedBean
@RequestScoped
public class Login {

    public boolean isAuthFailure() {
        String authFailureParam = FacesUtil.getRequestParameter("auth_failure");
        return ((authFailureParam != null) && authFailureParam.equals("true"));
    }   

    public boolean isRegistrationSuccess() {
        String registrationSuccess = FacesUtil.getRequestParameter("registration_success");
        return ((registrationSuccess != null) && registrationSuccess.equals("true"));
    }

}
