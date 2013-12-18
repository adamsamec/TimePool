package cz.timepool.testService;

import cz.timepool.bo.StatusEnum;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TermDto;
import cz.timepool.service.EventsService;
import cz.timepool.service.UsersService;
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
	private EventsService eventsService;

	@Autowired
	private UsersService usersService;

	@Test
	public void testAddRetrieveTermsOrdered() {
		Long authorId = usersService.addUser("Jmeno", "Prijmeni", "i@me.jl", "passss", "poppopopois");
		List<EventDto> events = eventsService.getAllEvents();
		int beforeEventsCount = events.size();
		
		Long eventId = eventsService.addEvent(authorId, "nazev", "lokace", "popis", new Date());
		events = eventsService.getAllEvents();
		assertEquals(beforeEventsCount + 1, events.size());

		String expectedTermDescription = "Nejnovejsi termin";
		Long term1Id = eventsService.addTermToEvent(new Date(7000000), StatusEnum.PLNY, "Novy termin", new Date(), authorId, eventId);
		Long term2Id = eventsService.addTermToEvent(new Date(2000000000), StatusEnum.PLNY, expectedTermDescription, new Date(), authorId, eventId);
		Long term3Id = eventsService.addTermToEvent(new Date(500), StatusEnum.PLNY, "Nejstarsi termin", new Date(), authorId, eventId);
		Long term4Id = eventsService.addTermToEvent(new Date(100000), StatusEnum.VOLNY, "Stary termin", new Date(), authorId, eventId);
		List<TermDto> terms = eventsService.getTermsByEventId(eventId);
		TermDto newestTerm = terms.get(terms.size() - 1);
		System.out.println(">>> TEST @OrderBy >>> Vypis terminu serazeny podle navrzeneho data (termDate):");
		for (TermDto term : terms) {
			System.out.println(term);
		}
		assertEquals(expectedTermDescription, newestTerm.getDescription());
	}
	
//	@Test
//	public void testAddRetreiveEventsBetweenDates() {
//		String expectedEventTitle = "Akce 4";
//		Long authorId = usersService.addUser("Akcni", "Clovek", "i@me.jl", "s jednoduchym heslem", "co si rad dela akce.");
//		Long event1Id = eventsService.addEvent(authorId, "Akce 1", "Misto", "Vsude akce!", new Date(100000));
//		Long event2Id = eventsService.addEvent(authorId, "Akce 2", "Misto", "Jenom akce!", new Date(200000));
//		Long event3Id = eventsService.addEvent(authorId, "Akce 3", "Misto", "Chci akci!", new Date(300000));
//		Long event4Id = eventsService.addEvent(authorId, expectedEventTitle, "Misto", "Co takhle nejakou.. akci!", new Date(400000));
//		Long event5Id = eventsService.addEvent(authorId, "Akce 5", "Misto", "Pro tentokrat radsi akci.", new Date(500000));
//		
//		List<EventDto> events = eventsService.getAllEventsCreatedBetween(new Date(200000), new Date(400000));
//		EventDto expectedEvent = events.get(events.size() - 1);
//		System.out.println(">>> TEST @NamedQuery >>> Vypis udalosti vytvorenych mezi datem " + new Date(200000) + " a " + new Date(400000));
//		for (EventDto event : events) {
//			System.out.println(event);
//		}
//		assertEquals(expectedEventTitle, expectedEvent.getTitle());
//	}

}
