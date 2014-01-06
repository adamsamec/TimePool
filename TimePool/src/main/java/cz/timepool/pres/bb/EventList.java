package cz.timepool.pres.bb;

import cz.timepool.dto.EventDto;
import cz.timepool.service.EventsServiceIface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
@Scope("request")
public class EventList {

    @Autowired
    private LoginSession loginSession;

    @Autowired
    private EventsServiceIface eventsService;

    public List<EventDto> getAllEvents() {
        return eventsService.getAllEvents();
    }

    public List<EventDto> getAllEventsByUser() {
        return eventsService.getAllEventsByUser(loginSession.getUser().getId());
    }

    public List<EventDto> getAllInvitedEventsByUser() {
        return eventsService.getAllInvitedEventsByUser(loginSession.getUser().getId());
    }

}
