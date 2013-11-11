package cz.timepool.testService;

import cz.timepool.dto.EventDto;
import cz.timepool.dto.TermDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Lukas L.
 */
public class EventServiceImplTest extends AbstractServiceTest {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TermService termService;

	@Test
	public void testAddEventAndRetrieveAndDelete() {
		Long idAuthor = userService.addUser("Jmeno", "Prijmeni", "imejl", "passss", "poppopopois");
		List<EventDto> events = eventService.getAllEvents();
		int before = events.size();
		Long idEvent = eventService.addEvent(idAuthor, "nazev", "lokace", "popis", new Date());
		events = eventService.getAllEvents();
		assertEquals(before + 1, events.size());
		
		Long idTerm = termService.addTermToEvent(new Date(100000), "volny asi", "stary termin", new Date(), idAuthor, idEvent);
		Long idTerm2 = termService.addTermToEvent(new Date(500), "volny asi", "nejstarsi termin", new Date(), idAuthor, idEvent);
		Long idTerm3 = termService.addTermToEvent(new Date(2000000000), "volny asi", "nejnovejsi termin", new Date(), idAuthor, idEvent);
		List<TermDto> terms = termService.getTermsByEventId(idEvent);
		TermDto lastTerm = terms.get(terms.size() - 1);
		System.out.println("TEST - Vypis terminu serazeny podle data:");
		for (TermDto termDto : terms) {
			System.out.println(termDto);
		}
		assertEquals("nejnovejsi termin", lastTerm.getDescription());
		
//		eventService.deleteEventById(idEvent);
//		events = eventService.getAllEvents();
//		assertEquals(before, events.size());
	}
}
