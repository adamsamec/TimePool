package cz.timepool.testService;

import cz.timepool.dto.TagDto;
import cz.timepool.service.EventsService;
import cz.timepool.service.UsersService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas L.
 */
public class TagServiceImplTest extends AbstractServiceTest {

	@Autowired
	private EventsService eventsService;

	@Autowired
	private UsersService usersService;

	@Test
	public void testAddRetrieveDeleteTags() {
		Long eventId = eventsService.addEvent(usersService.addUser("Lojza", "Polojza", "mejl", "passik", "pppppopis"), "Koupani ve studene vode ", "Rybnik v Rusku  ", "Bude to narez, prijdte vsichni.", new Date());
		List<TagDto> tags = eventsService.getTagsByEventId(eventId);
		int beforeTagsCount = tags.size();
		
		Long tagId = eventsService.addTagToEvent("ZIMA", eventId);
		tags = eventsService.getTagsByEventId(eventId);
		assertEquals(beforeTagsCount + 1, tags.size());

		eventsService.deleteTag(tagId);
		tags = eventsService.getTagsByEventId(eventId);
		assertEquals(beforeTagsCount, tags.size());
	}

}
