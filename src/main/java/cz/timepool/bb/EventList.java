
package cz.timepool.bb;

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
    public List<EventDto> getAllEventsByUser(){
	return eventsService.getAllEventsByUser(logged.getUser().getId());
    }
}
