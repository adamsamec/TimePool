package cz.timepool.testService;

import cz.timepool.dto.TermDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
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
	private TermService termService;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Test
	public void testAddChangeStatusRetrieveTerm() {
		Long authorId = userService.addUser("Lukas", "Lowinger", "mejl", "pass", "popis");
		Long eventId = this.addEvent(authorId);
		Long termId = termService.addTermToEvent(new Date(), "status terminu", "popis terminu ", new Date(), authorId, eventId);
		String expectedStatus = "ZMENA STATUSU";

		termService.changeTermStatusById(expectedStatus, termId);
		List<TermDto> terms = termService.getTermsByEventId(eventId);
		for (TermDto term : terms) {
			assertEquals(expectedStatus, term.getStatus());
		}
	}

	@Test
	public void testAddDeleteTerm() {
		Long authorId = userService.addUser("Lukas", "Druhej", "mejl", "pass", "popis");
		Long eventId = this.addEvent(authorId);
		Long termId = termService.addTermToEvent(new Date(), "statusek", "popisek", new Date(), authorId, eventId);

		List<TermDto> terms = termService.getTermsByEventId(eventId);
		int beforeTermsCount = terms.size();
		termService.deleteTermById(termId);
		assertEquals(beforeTermsCount - 1, termService.getTermsByEventId(eventId).size());
	}

	private Long addEvent(Long idAuthor) {
		return eventService.addEvent(idAuthor, "NAZEV", "LOKACE", "POPIS", new Date());
	}

}
