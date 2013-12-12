
package cz.timepool.bb;

import cz.timepool.helper.FacesUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lukas L.
 */
@ManagedBean
@RequestScoped
public class Login {

    private String passedParameter;

    public String getPassedParameter() {
        this.passedParameter = FacesUtil.getRequestParameter("login_error");
        return this.passedParameter;
    }

    // TODO: Tnto nesmyslny setter tu musi byt, aby se dodrzela specifikace Bean?
    public void setPassedParameter(String passedParameter) {
        this.passedParameter = passedParameter;
    }
}