package cz.timepool.bb;

import cz.timepool.dto.EventDto;
import cz.timepool.service.EventsService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class EventCreation {

    @Autowired
    EventsService eventsService;

    @Autowired
    Session logged;

    EventDto event;

    public EventDto getEvent() {
        if (event == null) {
            event = new EventDto(null, null, null, null, null, null, null, null, null);
        }
        return event;
    }

    // TODO: Tento zbytecny setter tu musi byt, aby se dodrzela specifikace Bean?
    public void setEvent(EventDto user) {
        this.event = user;
    }

    public String addEvent(String outcome) {
        eventsService.addEvent(logged.getUser().getId(), event.getTitle(), event.getLocation(), event.getDescription(), new Date());
        return outcome;
    }

}
