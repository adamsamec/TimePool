package cz.timepool.testService;

import cz.timepool.bo.StatusEnum;
import cz.timepool.dto.CommentDto;
import cz.timepool.service.CommentService;
import cz.timepool.service.EventService;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
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
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@Autowired
	private TermService termService;

	@Autowired
	private EventService eventService;

	@Test
	public void testAddRetrieveDeleteComments() {
		Long userId = userService.addUser("Jack" + System.currentTimeMillis(), "Tennessee", "muj@mejl.mm", "mojeheslo", "Neco o me");
		Long eventId = eventService.addEvent(userId, "Nazev udalosti", "Misto udalosti", "Popis udalosti", new Date());
		Long termId = termService.addTermToEvent(new Date(), StatusEnum.PLNY, "dsfsdfs", new Date(), userId, eventId);
		List<CommentDto> comments = commentService.getAllByTerm(termId);
		int beforeCommentsCount = comments.size();

		Long comment1Id = commentService.addCommentToTerm("HOCIJAKY TEXT KOMENTARE", userId, termId);
		Long comment2Id = commentService.addCommentToTerm("Jeste nejakej flejm sem pridam.", userId, termId);
		Long comment3Id = commentService.addCommentToTerm("A posledni, aby jsme se pokochali, jak to slovnikove rovna podle textu.", userId, termId);
		comments = commentService.getAllByTerm(termId);
		assertEquals(beforeCommentsCount + 3, comments.size());

		commentService.deleteCommentById(comment1Id);
		commentService.deleteCommentById(comment2Id);
		commentService.deleteCommentById(comment3Id);
		comments = commentService.getAllByTerm(termId);
		assertEquals(beforeCommentsCount, comments.size());

	}

}
