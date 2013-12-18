package cz.timepool.service;

import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.UserPermission;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TagDto;
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
public interface EventsServiceIface {

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId);

    @Secured({"ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents();

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<EventDto> getAllEventsByUser(Long userId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<EventDto> getAllInvitedEventsByUser(Long userId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    // TODO: predavat DTO
    public Long addEvent(Long author, String title, String location, String description, Date creationDate);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void editEventDetails(EventDto user);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    // TODO: predavat DTO
    public void deleteEventById(Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String inviteUser(Long eventId, String email, List<UserPermission> perms, String message, Date exp);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<TagDto> getTagsByEventId(Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Long addTagToEvent(String text, Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteTag(Long tag);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public TermDto getTermById(Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<TermDto> getTermsByEventId(Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Long addTermToEvent(Date termDate, StatusEnum status, String description, Date creationDate, Long authorId, Long eventId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void editTermById(TermDto editedTerm, Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void changeTermStatusById(StatusEnum status, Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void deleteTermById(Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public void addAcceptorToTermById(Long acceptorId, Long termId);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByEvent(Long eventId);

}
