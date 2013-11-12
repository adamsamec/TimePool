package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.Tag;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.dto.EventDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
class EventServiceImpl extends AbstractDataAccessService implements EventService {

	@Override
	public EventDto getEventById(Long eventId) {
		Event event = genericDao.getById(eventId, Event.class);
		EventDto eventDto = new EventDto(event.getId(), event.getAuthor().getId(), event.getTitle(), event.getLocation(), event.getDescription(), event.getCreationDate(), DtoTransformerHelper.getIdentifiers(event.getTags()), DtoTransformerHelper.getIdentifiers(event.getTerms()));
		return eventDto;
	}

	@Override
	public List<EventDto> getAllEvents() {
		List<EventDto> eventDtos = new ArrayList<EventDto>();
		List<Event> events = genericDao.getAll(Event.class);
		for (Event e : events) {
			eventDtos.add(new EventDto(e.getId(), e.getAuthor().getId(), e.getTitle(), e.getLocation(), e.getDescription(), e.getCreationDate(), DtoTransformerHelper.getIdentifiers(e.getTags()), DtoTransformerHelper.getIdentifiers(e.getTerms())));
		}
		return eventDtos;
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

	@Override
	public void deleteEventById(Long eventId) {
		genericDao.removeById(eventId, Event.class);
	}

	@Override
	public List<EventDto> getAllEventsCreatedBetween(Date fromDate, Date toDate) {
		List<Event> events = genericDao.getAllCreatedBetween(fromDate, toDate, Event.class);
		List<EventDto> eventDtos = new ArrayList<EventDto>();
		for (Event e : events) {
			eventDtos.add(new EventDto(e.getId(), e.getAuthor().getId(), e.getTitle(), e.getLocation(), e.getDescription(), e.getCreationDate(), DtoTransformerHelper.getIdentifiers(e.getTags()), DtoTransformerHelper.getIdentifiers(e.getTerms())));
		}
		return eventDtos;
	}

}
