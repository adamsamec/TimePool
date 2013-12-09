
package cz.timepool.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.EventDto;
import cz.timepool.service.EventsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component("eventList")
public class EventList {
    @Autowired
    EventsService eventsService;
    @Autowired
    LoggedUserBean logged;
    
    public boolean isEmpty;

    public void setIsEmpty(boolean isEmpty) {
	this.isEmpty = isEmpty;
    }
    
    public List<EventDto> getAllEventsByUser(){
	if(logged.getUser().getUserRole()==UserRole.ADMIN){
	    return eventsService.getAllEvents();
	}
	return eventsService.getAllEventsByUser(logged.getUser().getId());
    }
    
    public boolean getIsEmpty(){
	List<EventDto> list = getAllEventsByUser();
	for (EventDto eventDto : list) {
	    System.out.println(eventDto);
	}
	isEmpty = list.isEmpty();
	return isEmpty;
    }
}
