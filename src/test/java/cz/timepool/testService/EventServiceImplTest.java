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
	public void testAddRetrieveTermsOrdered() {
		Long authorId = userService.addUser("Jmeno", "Prijmeni", "i@me.jl", "passss", "poppopopois");
		List<EventDto> events = eventService.getAllEvents();
		int beforeEventsCount = events.size();
		
		Long eventId = eventService.addEvent(authorId, "nazev", "lokace", "popis", new Date());
		events = eventService.getAllEvents();
		assertEquals(beforeEventsCount + 1, events.size());

		String expectedTermDescription = "Nejnovejsi termin";
		Long term1Id = termService.addTermToEvent(new Date(7000000), "novy", "Novy termin", new Date(), authorId, eventId);
		Long term2Id = termService.addTermToEvent(new Date(2000000000), "novy", expectedTermDescription, new Date(), authorId, eventId);
		Long term3Id = termService.addTermToEvent(new Date(500), "novy", "Nejstarsi termin", new Date(), authorId, eventId);
		Long term4Id = termService.addTermToEvent(new Date(100000), "novy", "Stary termin", new Date(), authorId, eventId);
		List<TermDto> terms = termService.getTermsByEventId(eventId);
		TermDto newestTerm = terms.get(terms.size() - 1);
		System.out.println(">>> TEST @OrderBy >>> Vypis terminu serazeny podle navrzeneho data (termDate):");
		for (TermDto term : terms) {
			System.out.println(term);
		}
		assertEquals(expectedTermDescription, newestTerm.getDescription());
	}
	
	@Test
	public void testAddRetreiveEventsBetweenDates() {
		String expectedEventTitle = "Akce 4";
		Long authorId = userService.addUser("Akcni", "Clovek", "i@me.jl", "s jednoduchym heslem", "co si rad dela akce.");
		Long event1Id = eventService.addEvent(authorId, "Akce 1", "Misto", "Vsude akce!", new Date(100000));
		Long event2Id = eventService.addEvent(authorId, "Akce 2", "Misto", "Jenom akce!", new Date(200000));
		Long event3Id = eventService.addEvent(authorId, "Akce 3", "Misto", "Chci akci!", new Date(300000));
		Long event4Id = eventService.addEvent(authorId, expectedEventTitle, "Misto", "Co takhle nejakou.. akci!", new Date(400000));
		Long event5Id = eventService.addEvent(authorId, "Akce 5", "Misto", "Pro tentokrat radsi akci.", new Date(500000));
		
		List<EventDto> events = eventService.getAllEventsCreatedBetween(new Date(200000), new Date(400000));
		EventDto expectedEvent = events.get(events.size() - 1);
		System.out.println(">>> TEST @NamedQuery >>> Vypis udalosti vytvorenych mezi datem " + new Date(200000) + " a " + new Date(400000));
		for (EventDto event : events) {
			System.out.println(event);
		}
		assertEquals(expectedEventTitle, expectedEvent.getTitle());
	}

}
