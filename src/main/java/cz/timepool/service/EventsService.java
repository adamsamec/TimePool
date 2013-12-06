package cz.timepool.service;

import cz.timepool.bo.StatusEnum;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TagDto;
import cz.timepool.dto.TermDto;
import java.util.Date;
import java.util.List;
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

    public void deleteTag(Long tag);


    //TODO: addTermToEvent, deleteTermById, editTermById, getTermByEventId, changeTermStatusById, addParticipantToTermById 
    @Transactional(readOnly = true)
    public TermDto getTermById(Long termId);

    @Transactional(readOnly = true)
    public List<TermDto> getTermsByEventId(Long eventId);

    public Long addTermToEvent(Date termDate, StatusEnum status, String description, Date creationDate, Long authorId, Long eventId);

    public void deleteTermById(Long termId);

    public void editTermById(TermDto editedTerm, Long termId);

    public void changeTermStatusById(StatusEnum status, Long termId);

    public void addAcceptorToTermById(Long acceptorId, Long idTerm);
    
    
        @Transactional(readOnly = true)
    public EventDto getEventById(Long eventId);
 
    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents();

    @Transactional(readOnly = true)
    public List<EventDto> getAllEventsCreatedBetween(Date startDate, Date endDate);

    // TODO: predavat DTO
    public Long addEvent(Long author, String title, String location, String description, Date creationDate);

    // TODO: predavat DTO
    public void deleteEventById(Long eventId);
}
