package cz.timepool.service;

import cz.timepool.dto.EventDto;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
@Transactional
public interface EventService {

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
