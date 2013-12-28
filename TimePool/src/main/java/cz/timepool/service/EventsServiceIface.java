package cz.timepool.service;

import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.UserPermission;
import cz.timepool.dto.CommentDto;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TagDto;
import cz.timepool.dto.TermDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas L.
 */
@PreAuthorize("hasRole('ADMIN')")
@Transactional
public interface EventsServiceIface {

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId);

    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents();

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public List<EventDto> getAllEventsByUser(Long userId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public List<EventDto> getAllInvitedEventsByUser(Long userId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    // TODO: predavat DTO
    public Long addEvent(Long author, String title, String location, String description, Date creationDate);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void editEventDetails(EventDto user);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    // TODO: predavat DTO
    public void deleteEventById(Long eventId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String inviteUser(Long eventId, String email, List<UserPermission> perms, String message, Date exp);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public List<TagDto> getTagsByEventId(Long eventId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Long addTagToEvent(String text, Long eventId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteTag(Long tag);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public TermDto getTermById(Long termId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public List<TermDto> getTermsByEventId(Long eventId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Long addTermToEvent(Date termDate, StatusEnum status, String description, Date creationDate, Long authorId, Long eventId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void editTermById(TermDto editedTerm, Long termId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void changeTermStatusById(StatusEnum status, Long termId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteTermById(Long termId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void addAcceptorToTermById(Long acceptorId, Long termId);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByEvent(Long eventId);

}
