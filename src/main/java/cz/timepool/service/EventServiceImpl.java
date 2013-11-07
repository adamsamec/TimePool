
package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.Tag;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.dto.EventDto;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
class EventServiceImpl extends AbstractDataAccessService implements EventService{

    @Override
    public List<EventDto> getAllEvents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long addEvent(Long author, String title, String location, String description, Date creationDate) {
        Event event = new Event();
        event.setAuthor(genericDao.loadById(author, User.class));
        event.setCreationDate(creationDate);
        event.setDescription(description);
        event.setLocation(location);
        event.setTitle(title);
        return genericDao.saveOrUpdate(event).getId();
    }

}
