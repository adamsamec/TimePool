package cz.timepool.bb;

import cz.timepool.bo.UserPermission;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.UserDto;
import cz.timepool.helper.FacesUtil;
import cz.timepool.service.EventsService;
import cz.timepool.service.UsersService;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class SelectedEvent {

    protected EventDto event;
    @Autowired
    protected EventsService eventsService;
    
    public String[] permissions;
    private static Map<String, Object> permissionsValues;
    String userEmail;
    String message;

    static {
	permissionsValues = new LinkedHashMap<String, Object>();
	permissionsValues.put("ALL", UserPermission.ADD_TERM); //label, value
	permissionsValues.put("SMTH", UserPermission.ACCEPT_TERM);
	permissionsValues.put("DOnt KNOW", UserPermission.ADD_COMMENT);
    }

    public Map<String, Object> getPermissionsValues() {
	return permissionsValues;
    }

    public String getUserEmail() {
	return userEmail;
    }

    public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String[] getPermissions() {
	return permissions;
    }

    public void setPermissions(String[] permissions) {
	this.permissions = permissions;
    }

    public EventDto getEvent() {
	return event;
    }

    public void setEvent(EventDto event) {
	this.event = event;
    }

    public void setEventById2(Long eventId) {
	this.event = eventsService.getEventById(eventId);
    }

    public String deleteEvent() {
	Long userId = Long.valueOf(FacesUtil.getRequestParameter("deleteeventid"));
	eventsService.deleteEventById(userId);
	return "events";
    }

    public String setEventById(String outcome) {
	Long eventId = Long.valueOf(FacesUtil.getRequestParameter("eventid"));
	this.event = eventsService.getEventById(eventId);
	return outcome;
    }

    public String inviteToEvent() {
	System.out.println("emai " + getUserEmail() + "message" + getMessage());
	ArrayList<UserPermission> perm = new ArrayList<UserPermission>();
	for (String string : permissions) {
	    perm.add(UserPermission.convertFromString(string));
	}
	eventsService.inviteUser(event.getId(), userEmail, perm, message, new Date());
	return "";
    }
}
