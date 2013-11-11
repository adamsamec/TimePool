package cz.timepool.testService;

import cz.timepool.dto.TagDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TagService;
import cz.timepool.service.UserService;
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
	private TagService tagService;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Test
	public void testAddAndRetrieveAndDelete() {
		Long idEvent = eventService.addEvent(userService.addUser("lojza", "polojza", "mejl", "passik", "pppppopis"), "Koupani ve studene vode ", "Rybnik v Rusku  ", "Bude to narez, prijdte vsichni.", new Date());
		List<TagDto> tags = tagService.getAllTags();
		int before = tags.size();
		Long idTag = tagService.addTagToEvent("ZIMA", idEvent);
		tags = tagService.getAllTags();
		assertEquals(before + 1, tags.size());

		tagService.deleteTag(idTag);
		tags = tagService.getAllTags();
		assertEquals(before, tags.size());
	}
}
