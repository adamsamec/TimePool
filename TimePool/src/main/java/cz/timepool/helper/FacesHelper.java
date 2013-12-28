package cz.timepool.helper;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lukas L.
 */
public class FacesHelper {

    public static String getRequestParameter(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    public static String getRequestURL() {
        return (String) ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString();
    }

    /**
     * Adds a messaage with the @null client identifier.
     *
     * @param message
     */
    public static void addMessage(String message) {
        FacesHelper.addMessage(null, message);
    }

    public static void addMessage(FacesMessage message) {
        FacesHelper.addMessage(null, message);
    }
    
    public static void addMessage(String clientId, String message) {
        FacesHelper.addMessage(clientId, new FacesMessage(message));
    }
    
    public static void addMessage(String clientId, FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }

    /**
     * Gets the message from the specified bundle.
     *
     * @param bundleName bundle baseName
     * @param key key of the message
     * @return message
     */
    public static String getMessage(String bundleName, String key) {
        return ResourceBundle.getBundle(bundleName).getString(key);
    }

    public static Object getSessionAttribute(String key) {
        return ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute(key);
    }

    public static void setSessionAttribute(String key, Object value) {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute(key, value);
    }

    /**
     * Returns the remote address for this request.
     *
     * @return remote address
     */
    public static String getRemoteAddress() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
    }

}
