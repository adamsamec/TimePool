package cz.timepool.convertors;

import cz.timepool.bo.UserPermission;
import cz.timepool.service.EventsServiceIface;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class PermissionConverter implements Converter {

    @Autowired
    EventsServiceIface eventsService;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return UserPermission.fromString(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object instanceof UserPermission) {
            return ((UserPermission) object).toString();
        }
        return "";
    }

}
