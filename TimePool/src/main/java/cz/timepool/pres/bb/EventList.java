package cz.timepool.pres.bb;

import cz.timepool.bo.UserRole;
import cz.timepool.dto.EventDto;
import cz.timepool.service.EventsServiceIface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class EventList {

    @Autowired
    private LoginSession loginSession;

    @Autowired
    private EventsServiceIface eventsService;

    public List<EventDto> getAllEventsByUser() {
        if (loginSession.getUser().getUserRole() == UserRole.ADMIN) {
            return eventsService.getAllEvents();
        }
        return eventsService.getAllEventsByUser(loginSession.getUser().getId());
    }

    public List<EventDto> getAllInvitedEventsByUser() {
        return eventsService.getAllInvitedEventsByUser(loginSession.getUser().getId());
    }

    public boolean isIsAllEventsEmpty() {
        return getAllEventsByUser().isEmpty();
    }

    public boolean isIsAllInvitedeventsEmpty() {
        return getAllInvitedEventsByUser().isEmpty();
    }

}
