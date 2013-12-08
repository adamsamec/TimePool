package cz.timepool.helper;

import javax.faces.context.FacesContext;

/**
 *
 * @author Lukas L.
 */
public class FacesUtil {

    public static String getRequestParameter(String name) {
	return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
}
