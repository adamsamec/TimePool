package cz.timepool.service;

import cz.timepool.bo.Comment;
import cz.timepool.bo.Event;
import cz.timepool.bo.EventInvitation;
import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.Tag;
import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.bo.UserPermission;
import cz.timepool.bo.UserRole;
import cz.timepool.dao.EventDaoIface;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.EventInvitationDto;
import cz.timepool.dto.TagDto;
import cz.timepool.dto.TermDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class EventsService extends TimepoolService implements EventsServiceIface {

    @Autowired
    protected EventDaoIface eventDao;

    @Override
    public EventDto getEventById(Long eventId) {
        Event event = this.timepoolDao.getById(eventId, Event.class);
        EventDto eventDto = new EventDto(event.getId(), event.getAuthor().getId(), event.getTitle(), event.getLocation(), event.getDescription(), event.getCreationDate(), DtoTransformerHelper.getIdentifiers(event.getTags()), DtoTransformerHelper.getIdentifiers(event.getTerms()), DtoTransformerHelper.getIdentifiers(event.getComments()));
        return eventDto;
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<EventDto> eventDtos = new ArrayList<EventDto>();
        List<Event> events = this.timepoolDao.getAll(Event.class);
        for (Event event : events) {
            eventDtos.add(new EventDto(event.getId(), event.getAuthor().getId(), event.getTitle(), event.getLocation(), event.getDescription(), event.getCreationDate(), DtoTransformerHelper.getIdentifiers(event.getTags()), DtoTransformerHelper.getIdentifiers(event.getTerms()), DtoTransformerHelper.getIdentifiers(event.getComments())));
        }
        return eventDtos;
    }

    @Override
    public List<EventDto> getAllEventsByUser(Long userId) {
        List<Event> events = timepoolDao.loadById(userId, User.class).getAuthoredEvents();
        List<EventDto> eventsDto = new ArrayList<EventDto>();
        for (Event event : events) {
            eventsDto.add(new EventDto(event.getId(), event.getAuthor().getId(), event.getTitle(), event.getLocation(), event.getDescription(), event.getCreationDate(), DtoTransformerHelper.getIdentifiers(event.getTags()), DtoTransformerHelper.getIdentifiers(event.getTerms()), DtoTransformerHelper.getIdentifiers(event.getComments())));
        }
        return eventsDto;
    }

    @Override
    public List<EventDto> getAllInvitedEventsByUser(Long userId) {
        User user = timepoolDao.loadById(userId, User.class);
        List<EventInvitation> eventInvitations = user.getEventInvitations();
        List<EventInvitationDto> eiDto = new ArrayList<EventInvitationDto>();
        for (EventInvitation eventInvitation : eventInvitations) {
            eiDto.add(new EventInvitationDto(eventInvitation.getId(), eventInvitation.getPermissions(), eventInvitation.getMessage(), eventInvitation.getExpirationDate(), eventInvitation.getCreationDate(), eventInvitation.getEvent().getId(), eventInvitation.getInvitedUser().getId()));
        }
        List<EventDto> evDto = getAllEvents();
        int size = evDto.size();
        for (int i = 0; i < size; i++) {
            if (!containsEventId(eiDto, evDto.get(i))) {
                evDto.remove(i);
                size--;
                i--;
            }
        }
        return evDto;
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
    public void editEventDetails(EventDto eventDto) {
        Event event = timepoolDao.loadById(eventDto.getId(), Event.class);
        event.setTitle(eventDto.getTitle());
        event.setLocation(eventDto.getLocation());
        event.setDescription(eventDto.getDescription());
        timepoolDao.save(event);
    }

    @Override
    public void deleteEventById(Long eventId) {
        timepoolDao.removeById(eventId, Event.class);
    }

    @Override
    public String inviteUser(Long eventId, String userEmail, List<UserPermission> perms, String message, Date exp) {
        Event event = timepoolDao.loadById(eventId, Event.class);
        User user;
        try {
            user = timepoolDao.getByPropertyUnique("email", userEmail, User.class);
        } catch (NoResultException ex) {
            ex.printStackTrace();
            user = new User();
            user.setEmail(userEmail);
            user.setAuthKey(new Random().nextInt()); // TODO: jiny algoritmus
            user.setCreationDate(new Date());
            user.setUserRole(UserRole.UNREGISTERED);
            timepoolDao.save(user);
        }

        EventInvitation eventInvitation = new EventInvitation();
        eventInvitation.setInvitedUser(user);
        eventInvitation.setEvent(event);
        eventInvitation.setExpirationDate(exp);
        eventInvitation.setCreationDate(new Date());
        eventInvitation.setPermissions(perms);
        eventInvitation.setMessage(message);
        timepoolDao.save(eventInvitation);
        return "cislo";
    }

    @Override
    public List<TagDto> getTagsByEventId(Long eventId) {
        Event event = this.timepoolDao.loadById(eventId, Event.class);
        List<Tag> tags = event.getTags();
        List<TagDto> tagDtos = new ArrayList<TagDto>();
        for (Tag tag : tags) {
            tagDtos.add(new TagDto(tag.getId(), DtoTransformerHelper.getIdentifiers(tag.getEvents()), tag.getText()));
        }
        return tagDtos;
    }

    @Override
    public Long addTagToEvent(String text, Long eventId) {
        Tag tag = new Tag();
        tag.setText(text);
        Event event = this.timepoolDao.loadById(eventId, Event.class);
        tag.addEvent(event);
        event.addTag(tag);
        return this.timepoolDao.save(tag).getId();
    }

    @Override
    public void deleteTag(Long tag) {
        timepoolDao.removeById(tag, Tag.class);
    }

    @Override
    public TermDto getTermById(Long termId) {
        Term term = this.timepoolDao.getById(termId, Term.class);
        TermDto eventDto = new TermDto(term.getId(), term.getTermDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), term.getEvent().getId(), DtoTransformerHelper.getIdentifiers(term.getAcceptors()));
        return eventDto;
    }

    @Override
    public List<TermDto> getTermsByEventId(Long eventId) {
        Event event = this.timepoolDao.loadById(eventId, Event.class);
        List<Term> terms = event.getTerms();
        List<TermDto> termDtos = new ArrayList<TermDto>();
        for (Term term : terms) {
            termDtos.add(new TermDto(term.getId(), term.getTermDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), term.getEvent().getId(), DtoTransformerHelper.getIdentifiers(term.getAcceptors())));
        }
        return termDtos;
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
    public void editTermById(TermDto changedTerm, Long termId) {
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
    public void deleteTermById(Long termId) {
        Term term = this.timepoolDao.loadById(termId, Term.class);
        Event event = term.getEvent();
        event.removeTerm(term);
        timepoolDao.save(event);
    }

    @Override
    public void addAcceptorToTermById(Long acceptorId, Long termId) {
        User acceptor = this.timepoolDao.loadById(acceptorId, User.class);
        Term term = this.timepoolDao.loadById(termId, Term.class);
        term.addAcceptor(acceptor);
        timepoolDao.save(term);
    }

    @Override
    public List<CommentDto> getAllCommentsByEvent(Long eventId) {
        Event evnt = timepoolDao.loadById(eventId, Event.class);
        List<Term> terms = evnt.getTerms();
        List<Comment> comments = evnt.getComments();
        ArrayList<CommentDto> cd = new ArrayList<CommentDto>();
        for (Comment comment : comments) {
            cd.add(new CommentDto(comment.getId(), comment.getAuthor().getId(), comment.getEvent().getId(), comment.getText(), comment.getCreationDate()));
        }
        return cd;
    }

    private boolean containsEventId(List<EventInvitationDto> eventInviationsDtos, EventDto eventDto) {
        for (EventInvitationDto eventInvitationDto : eventInviationsDtos) {
            if (eventInvitationDto.getEvent().equals(eventDto.getId())) {
                return true;
            }
        }
        return false;
    }

}
