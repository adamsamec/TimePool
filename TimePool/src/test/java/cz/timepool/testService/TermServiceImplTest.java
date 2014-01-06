package cz.timepool.testService;

import cz.timepool.bo.StatusEnum;
import cz.timepool.dto.TermDto;
import cz.timepool.service.EventsServiceIface;
import cz.timepool.service.UsersServiceIface;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas L.
 */
public class TermServiceImplTest extends AbstractServiceTest {

	@Autowired
	private EventsServiceIface eventsService;

	@Autowired
	private UsersServiceIface usersService;

	@Test
	public void testAddChangeStatusRetrieveTerm() {
		Long authorId = usersService.addUser("Lukas", "Lowinger", "mejl1", "pass", "popis");
		Long eventId = this.addEvent(authorId);
		Long termId = eventsService.addTermToEvent(new Date(), StatusEnum.FULL, "popis terminu ", new Date(), authorId, eventId);
		StatusEnum expectedStatus = StatusEnum.FULL;

		eventsService.changeTermStatusById(expectedStatus, termId);
		List<TermDto> terms = eventsService.getTermsByEventId(eventId);
		for (TermDto term : terms) {
			assertEquals(expectedStatus, term.getStatus());
		}
	}

	@Test
	public void testAddDeleteTerm() {
		Long authorId = usersService.addUser("Lukas", "Druhej", "mejl2", "pass", "popis");
		Long eventId = this.addEvent(authorId);
		Long termId = eventsService.addTermToEvent(new Date(), StatusEnum.FREE, "Termin, ktery bude vzapeti smazan", new Date(), authorId, eventId);

		List<TermDto> terms = eventsService.getTermsByEventId(eventId);
		int beforeTermsCount = terms.size();
		eventsService.deleteTermById(termId);
		assertEquals(beforeTermsCount - 1, eventsService.getTermsByEventId(eventId).size());
	}

	private Long addEvent(Long idAuthor) {
		return eventsService.addEvent(idAuthor, "NAZEV", "LOKACE", "POPIS", new Date());
	}

}
