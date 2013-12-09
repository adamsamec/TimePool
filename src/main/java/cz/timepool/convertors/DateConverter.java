
package cz.timepool.convertors;

import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Lukas L.
 */
public class DateConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
	return new Date(string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
	return o.toString();
    }

}
