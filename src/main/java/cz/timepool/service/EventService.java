package cz.timepool.service;

import cz.timepool.dto.EventDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public interface EventService extends GenericService {

	//TODO: dopsat metody
	public EventDto getEventById(Long eventId);

	public List<EventDto> getAllEvents();

	// TODO: predavat DTO
	public Long addEvent(Long author, String title, String location, String description, Date creationDate);

	// TODO: predavat DTO
	public void deleteEventById(Long eventId);

	public List<EventDto> getAllEventsCreatedBetween(Date startDate, Date endDate);

}
