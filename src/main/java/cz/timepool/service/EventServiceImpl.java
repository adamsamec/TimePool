package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.User;
import cz.timepool.dao.EventDaoIface;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TagDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
class EventServiceImpl extends GenericService implements EventService {
    
    @Autowired
    protected EventDaoIface eventDao;

	@Override
	public EventDto getEventById(Long eventId) {
		Event event = this.timepoolDao.getById(eventId, Event.class);
		EventDto eventDto = new EventDto(event.getId(), event.getAuthor().getId(), event.getTitle(), event.getLocation(), event.getDescription(), event.getCreationDate(), DtoTransformerHelper.getIdentifiers(event.getTags()), DtoTransformerHelper.getIdentifiers(event.getTerms()));
		return eventDto;
	}

	@Override
	public List<EventDto> getAllEvents() {
		List<EventDto> eventDtos = new ArrayList<EventDto>();
		List<Event> events = this.timepoolDao.getAll(Event.class);
		for (Event e : events) {
			eventDtos.add(new EventDto(e.getId(), e.getAuthor().getId(), e.getTitle(), e.getLocation(), e.getDescription(), e.getCreationDate(), DtoTransformerHelper.getIdentifiers(e.getTags()), DtoTransformerHelper.getIdentifiers(e.getTerms())));
		}
		return eventDtos;
	}

	@Override
	public Long addEvent(Long author, String title, String location, String description, Date creationDate) {
		Event event = new Event();
		event.setAuthor(this.timepoolDao.loadById(author, User.class));
		event.setCreationDate(creationDate);
		event.setDescription(description);
		event.setLocation(location);
		event.setTitle(title);
		return this.timepoolDao.save(event).getId();
	}

	@Override
	public void deleteEventById(Long eventId) {
		timepoolDao.removeById(eventId, Event.class);
	}

	@Override
	public List<EventDto> getAllEventsCreatedBetween(Date fromDate, Date toDate) {
		List<EventDto> dtos = this.getAllCreatedBetween(fromDate, toDate, Event.class, EventDto.class);
		return dtos;
//		List<Event> events = this.genericDao.getAllBetween("creationDate", fromDate, toDate, Event.class);
//		List<EventDto> eventDtos = new ArrayList<EventDto>();
//		for (Event e : events) {
//			eventDtos.add(new EventDto(e.getId(), e.getAuthor().getId(), e.getTitle(), e.getLocation(), e.getDescription(), e.getCreationDate(), DtoTransformerHelper.getIdentifiers(e.getTags()), DtoTransformerHelper.getIdentifiers(e.getTerms())));
//		}
//		return eventDtos;
	}
    
//    public List<EventDto> getAllEventsWithTags(List<TagDto> tags) {
//		this.eventDao.getAllEventsWithTags(null);
//	}

}
