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
@Component
public class EventList {

    @Autowired
    EventsService eventsService;
    @Autowired
    Session logged;

    public boolean isAllEventsEmpty;
    public boolean isAllInvitedeventsEmpty;

    public void setIsAllEventsEmpty(boolean isAllEventsEmpty) {
	this.isAllEventsEmpty = isAllEventsEmpty;
    }

    public void setIsAllInvitedeventsEmpty(boolean isAllInvitedeventsEmpty) {
	this.isAllInvitedeventsEmpty = isAllInvitedeventsEmpty;
    }

    public List<EventDto> getAllEventsByUser() {
        if (logged.getUser().getUserRole() == UserRole.ADMIN) {
            return eventsService.getAllEvents();
        }
        return eventsService.getAllEventsByUser(logged.getUser().getId());
    }

    public boolean isIsAllEventsEmpty() {
	isAllEventsEmpty = getAllEventsByUser().isEmpty();
	return isAllEventsEmpty;
    }

    public boolean isIsAllInvitedeventsEmpty() {
	isAllInvitedeventsEmpty = getAllInvitedEventsByUser().isEmpty();
	return isAllInvitedeventsEmpty;
    }

    public List<EventDto> getAllInvitedEventsByUser(){
	return eventsService.getAllInvitedEventsByUser(logged.getUser().getId());
    }

}
