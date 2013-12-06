package cz.timepool.testService;

import cz.timepool.bo.StatusEnum;
import cz.timepool.dto.TermDto;
import cz.timepool.service.EventsService;
import cz.timepool.service.UsersService;
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
	private EventsService eventsService;

	@Autowired
	private UsersService usersService;

	@Test
	public void testAddChangeStatusRetrieveTerm() {
		Long authorId = usersService.addUser("Lukas", "Lowinger", "mejl", "pass", "popis");
		Long eventId = this.addEvent(authorId);
		Long termId = eventsService.addTermToEvent(new Date(), StatusEnum.PLNY, "popis terminu ", new Date(), authorId, eventId);
		StatusEnum expectedStatus = StatusEnum.PLNY;

		eventsService.changeTermStatusById(expectedStatus, termId);
		List<TermDto> terms = eventsService.getTermsByEventId(eventId);
		for (TermDto term : terms) {
			assertEquals(expectedStatus, term.getStatus());
		}
	}

	@Test
	public void testAddDeleteTerm() {
		Long authorId = usersService.addUser("Lukas", "Druhej", "mejl", "pass", "popis");
		Long eventId = this.addEvent(authorId);
		Long termId = eventsService.addTermToEvent(new Date(), StatusEnum.PLNY, "popisek", new Date(), authorId, eventId);

		List<TermDto> terms = eventsService.getTermsByEventId(eventId);
		int beforeTermsCount = terms.size();
		eventsService.deleteTermById(termId);
		assertEquals(beforeTermsCount - 1, eventsService.getTermsByEventId(eventId).size());
	}

	private Long addEvent(Long idAuthor) {
		return eventsService.addEvent(idAuthor, "NAZEV", "LOKACE", "POPIS", new Date());
	}

}
