package cz.timepool.service;

import cz.timepool.bo.AbstractBusinessObject;
import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.UserPermission;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TagDto;
import cz.timepool.dto.TemporalEntityDto;
import cz.timepool.dto.TermDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas L.
 */
@Transactional
public interface EventsService {
    
    public Long addTagToEvent(String text, Long event);

    //public Long addTag(String text);
    @Transactional(readOnly = true)
    public List<TagDto> getAllTags();
    
    @Secured({"ROLE_ADMIN"})
    public void deleteTag(Long tag);


    //TODO: addTermToEvent, deleteTermById, editTermById, getTermByEventId, changeTermStatusById, addParticipantToTermById 
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public TermDto getTermById(Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<TermDto> getTermsByEventId(Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Long addTermToEvent(Date termDate, StatusEnum status, String description, Date creationDate, Long authorId, Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteTermById(Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void editTermById(TermDto editedTerm, Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})//TODO: nejsem si jist
    public void changeTermStatusById(StatusEnum status, Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void addAcceptorToTermById(Long acceptorId, Long idTerm);
    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId);
 
    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents();

    //@Transactional(readOnly = true)
    //public List<EventDto> getAllEventsCreatedBetween(Date startDate, Date endDate);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    // TODO: predavat DTO
    public Long addEvent(Long author, String title, String location, String description, Date creationDate);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    // TODO: predavat DTO
    public void deleteEventById(Long eventId);
    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<EventDto> getAllEventsByUser(Long userId);
    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String inviteUser(Long eventId, String email, List<UserPermission> perms, String message, Date exp);
    //public String inviteAnonymousUser(Long eventId, String userEmail, List<UserPermission> perms, String message, Date exp);
    
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByEvent(Long idEvent);
}
