
package cz.timepool.bb;

import cz.timepool.service.EventsService;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class EventFormBean {
    @Autowired
    EventsService eventsService;
    @Autowired
    LoggedUserBean logged;
    String description;
    String title;
    String location;

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }
    
    public String addEvent(){
	eventsService.addEvent(logged.getUser().getId(), getTitle(), getLocation(), getDescription(), new Date());
	return "events";
    }
    public EventsService getEventsService() {
	return eventsService;
    }

    public void setEventsService(EventsService eventsService) {
	this.eventsService = eventsService;
    }
    
    
}
