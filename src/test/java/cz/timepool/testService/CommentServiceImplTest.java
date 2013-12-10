package cz.timepool.testService;

import cz.timepool.bo.StatusEnum;
import cz.timepool.dto.CommentDto;
import cz.timepool.service.EventsService;
import cz.timepool.service.UsersService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas Lowinger
 */
public class CommentServiceImplTest extends AbstractServiceTest {

	@Autowired
	private UsersService usersService;


	@Autowired
	private EventsService eventsService;

	@Test
	public void testAddRetrieveDeleteComments() {
		Long userId = usersService.addUser("Jack" + System.currentTimeMillis(), "Tennessee", "muj@mejl.mm", "mojeheslo", "Neco o me");
		Long eventId = eventsService.addEvent(userId, "Nazev udalosti", "Misto udalosti", "Popis udalosti", new Date());
		//Long termId = eventsService.addTermToEvent(new Date(), StatusEnum.PLNY, "dsfsdfs", new Date(), userId, eventId);
		List<CommentDto> comments = eventsService.getAllCommentsByEvent(eventId);
		int beforeCommentsCount = comments.size();

		Long comment1Id = usersService.addCommentToEvent("HOCIJAKY TEXT KOMENTARE", userId, eventId);
		Long comment2Id = usersService.addCommentToEvent("Jeste nejakej flejm sem pridam.", userId, eventId);
		Long comment3Id = usersService.addCommentToEvent("A posledni, aby jsme se pokochali, jak to slovnikove rovna podle textu.", userId, eventId);
		comments = eventsService.getAllCommentsByEvent(eventId);
		assertEquals(beforeCommentsCount + 3, comments.size());

		usersService.deleteCommentById(comment1Id);
		usersService.deleteCommentById(comment2Id);
		usersService.deleteCommentById(comment3Id);
		comments = eventsService.getAllCommentsByEvent(eventId);
		assertEquals(beforeCommentsCount, comments.size());

	}

}
