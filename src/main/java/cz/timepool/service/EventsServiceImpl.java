package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.Tag;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.dao.EventDaoIface;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TagDto;
import cz.timepool.dto.TermDto;
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
public class EventsServiceImpl extends GenericService implements EventsService {

    @Override
    public Long addTagToEvent(String text, Long event) {
	Tag t = new Tag();
	t.setText(text);
	Event e = this.timepoolDao.loadById(event, Event.class);
	t.addEvent(e);
	e.addTag(t);
	return this.timepoolDao.save(t).getId();
    }

    @Override
    public List<TagDto> getAllTags() {
	List<TagDto> tagDtos = new ArrayList<TagDto>();
	List<Tag> tags = this.timepoolDao.getAll(Tag.class);
	for (Tag tag : tags) {
	    tagDtos.add(new TagDto(tag.getId(), DtoTransformerHelper.getIdentifiers(tag.getEvents()), tag.getText()));
	}
	return tagDtos;
    }

    @Override
    public void deleteTag(Long tag) {
	timepoolDao.removeById(tag, Tag.class);
    }

    @Override
    public TermDto getTermById(Long termId) {
	Term term = this.timepoolDao.getById(termId, Term.class);
	TermDto eventDto = new TermDto(term.getId(), term.getTermDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), term.getEvent().getId(), DtoTransformerHelper.getIdentifiers(term.getAcceptors()), DtoTransformerHelper.getIdentifiers(term.getComments()));
	return eventDto;
    }

    @Override
    public List<TermDto> getTermsByEventId(Long idEvent) {
	Event event = this.timepoolDao.loadById(idEvent, Event.class);
	List<Term> terms = event.getTerms();
	List<TermDto> termsDto = new ArrayList<TermDto>();
	for (Term term : terms) {
	    termsDto.add(new TermDto(term.getId(), term.getTermDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), term.getEvent().getId(), DtoTransformerHelper.getIdentifiers(term.getAcceptors()), DtoTransformerHelper.getIdentifiers(term.getComments())));
	}
	return termsDto;
    }

    @Override
    public Long addTermToEvent(Date termDate, StatusEnum status, String description, Date creationDate, Long author, Long event) {
	Term term = new Term();
	term.setCreationDate(creationDate);
	term.setTermDate(termDate);
	term.setDescription(description);
	term.setStatus(status);
	term.setAuthor(this.timepoolDao.loadById(author, User.class));
	term.setEvent(this.timepoolDao.loadById(event, Event.class));
	return this.timepoolDao.save(term).getId();
    }

    @Override
    public void deleteTermById(Long idTerm) {
//        genericDao.removeById(idTerm, Term.class);
	Term term = this.timepoolDao.loadById(idTerm, Term.class);
	Event event = term.getEvent();
	event.removeTerm(term);
    }

    @Override
    public void editTermById(TermDto changedTerm, Long idTerm) {
	Term term = new Term();
	term.setCreationDate(changedTerm.getCreationDate());
	term.setTermDate(changedTerm.getTermDate());
	term.setDescription(changedTerm.getDescription());
	term.setStatus(changedTerm.getStatus());
	term.setAuthor(this.timepoolDao.loadById(changedTerm.getAuthor(), User.class));
//        term.setAcceptors(DtoTransformerHelper.getIdentifiers(changedTerm.get));
	timepoolDao.save(term);
    }

    @Override
    public void changeTermStatusById(StatusEnum status, Long termId) {
	Term term = this.timepoolDao.loadById(termId, Term.class);
	term.setStatus(status);
	timepoolDao.save(term);
    }

    @Override
    public void addAcceptorToTermById(Long idAcceptor, Long idTerm) {
	User acceptor = this.timepoolDao.loadById(idAcceptor, User.class);
	Term term = this.timepoolDao.loadById(idTerm, Term.class);
	term.addAcceptor(acceptor);
	timepoolDao.save(term);
    }
    
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
//	
//		return this.eventDao.getAllEventsWithTags(tags);
//	}
}