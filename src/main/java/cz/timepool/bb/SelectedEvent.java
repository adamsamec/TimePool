
package cz.timepool.bb;

import cz.timepool.dto.EventDto;
import cz.timepool.service.EventsService;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
@SessionScoped
public class SelectedEvent {
    
    protected EventDto event;

    @Autowired
    protected EventsService eventsService;

    public EventDto getEvent() {
	return event;
    }

    public void setEvent(EventDto event) {
	this.event = event;
    }

    public void setEventById2(Long eventId) {
        this.event = eventsService.getEventById(eventId);
    }
    
    public String setEventById(String outcome) {
	System.out.println("outcame : "+outcome);
        Long userId = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventid"));
        this.event = eventsService.getEventById(userId);
        return outcome;
    }
    
}
