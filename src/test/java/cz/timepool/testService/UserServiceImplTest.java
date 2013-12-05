package cz.timepool.testService;

import cz.timepool.dto.EventDto;
import cz.timepool.dto.TermDto;
import cz.timepool.dto.UserDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TagService;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas Lowinger
 */
public class UserServiceImplTest extends AbstractServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@Autowired
	private TagService tagService;

	@Autowired
	private TermService termService;

	public UserServiceImplTest() {
		super();
	}

	@Test
	public void testAddRetrieveUser() {
		String name = "Budu" + System.currentTimeMillis();
		String surname = "Navracen" + System.currentTimeMillis();
		String password = "Heslo" + System.currentTimeMillis();
		String email = "E-mail" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();

		Long userId = userService.addUser(name, surname, email, password, description);
		UserDto user = userService.getUserById(userId);
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
	}

	@Test
	public void testAddRetrieveUsersOrdered() {
		String expectedUserName = "Vincent (prisel druhy, ale je posledni, to jsou veci!)";
		Long user1Id = this.addUser("Jarda");
		Long user2Id = this.addUser(expectedUserName);
		Long user3Id = this.addUser("Pepa");
		Long user4Id = this.addUser("Alfred");

		List<UserDto> users = userService.getAllUsersOrderedByName();
		UserDto alphabeticallyLastUser = users.get(users.size() - 1);
		System.out.println(">>> TEST >>> Vypis uzivatelu serazeny podle jmena:");
		for (UserDto user : users) {
			System.out.println(user.getName());
		}
		assertEquals(expectedUserName, alphabeticallyLastUser.getName());
	}

	@Test
	public void testAddRemoveUser() {
		String name = "Budu" + System.currentTimeMillis();
		String surname = "Odstranen" + System.currentTimeMillis();
		String password = "Heslo" + System.currentTimeMillis();
		String email = "emauj@nojo.no" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();
		int beforeUsersCount = userService.getAllUsers().size();

		Long userId = userService.addUser(name, surname, email, password, description);
		assertEquals(beforeUsersCount + 1, userService.getAllUsers().size());

		userService.deleteUser(userId);
		assertEquals(beforeUsersCount, userService.getAllUsers().size());
	}

	@Test
	public void testAddUserAddEventsRemoveUser() {
		String name = "Budu" + System.currentTimeMillis();
		String surname = "Odstranen i s mymi akcemi" + System.currentTimeMillis();
		String password = "Heslo" + System.currentTimeMillis();
		String email = "emauj@nojo.no" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();
		// TODO: Vytvaret a predavat DTO misto property
		Long authorId = userService.addUser(name, surname, email, password, description);
		Long event1Id = eventService.addEvent(authorId, "Akce 1", "Misto", "Vsude akce!", new Date(100000));
		Long event2Id = eventService.addEvent(authorId, "Akce 2", "Misto", "Jenom akce!", new Date(200000));
		Long event3Id = eventService.addEvent(authorId, "Akce 3", "Misto", "Chci akci!", new Date(300000));
		int beforeEventsCount = eventService.getAllEvents().size();

		userService.deleteUser(authorId);
		int afterEventsCount = eventService.getAllEvents().size();
		System.out.println(">>> TEST CascadeType.REMOVE >>> Kaskadni mazani akci po smazani uzivatele:");
		System.out.println("Celkem akci pred smazanim: " + beforeEventsCount);
		System.out.println("Celkem akci po smazani: " + afterEventsCount);
		assertEquals(beforeEventsCount - 3, afterEventsCount);
	}

	/**
	 * Tento test popisuje nasledujici realny use-case:
	 * (1) Uzivatel v roli autora vytvori akci.
	 * (2) Jiny uzivatel navhrne nekolik terminu do teto akce.
	 * (3) Dale stejny uzivatel jeden z techto terminu sam akceptuje, cimz se stane akceptantem.
	 * (4) Autor zmeni status nejvzdalenejsiho terminu navrzeneho akceptantem na "zamitnut".
	 * (5) Autor se prida do nejblizsiho terminu navrzaneho akceptantem.
	 * (6) Autor k akci prida tag v nadeji, ze se na ni prihlasi vice uzivatelu :-).
	 */
	@Test
	public void testUseCase1() {
		Long authorId = userService.addUser("Autor", "Eventu", "nejaky@email.cz", "heslo", "Tento uzivatel to ma vse pod palcem a nakonec se prida do terminu.");
		Long user1Id = userService.addUser("Akceptant", "Eventu", "nejadsfsdky@email.cz", "heslo", "Tento uzivatel navhrne nejake terminy a do jednoho se take prida.");
		// (1):
		Long eventId = eventService.addEvent(authorId, "Nova suprova akce", "Kdesi", "Popis akce.", new Date());
		// (2):
		Long term2Id = termService.addTermToEvent(new Date(1388322514000L), "novy", "Druhy nejblizsi termin", new Date(), user1Id, eventId);
		Long term1Id = termService.addTermToEvent(new Date(1398322514000L), "novy", "Treti nejblizsi termin", new Date(), user1Id, eventId);
		Long term3Id = termService.addTermToEvent(new Date(1386522514000L), "novy", "Prvni nejblizsi termin", new Date(), user1Id, eventId);
		// (3):
		termService.addAcceptorToTermById(user1Id, term2Id);
		// (4):
		termService.changeTermStatusById("zamitnut", term1Id);
		// (5):
		termService.addAcceptorToTermById(authorId, term3Id);
		// (6):
		Long tagId = tagService.addTagToEvent("Limited edition akce, c'mon!", eventId);
		
		EventDto eventDto = eventService.getEventById(eventId);
		List<Long> termIds = eventDto.getTerms();
		System.out.println(">>> TEST UseCase1 >>> Terminy akce " + eventDto + ":");
		System.out.println("Autor id: " + authorId);
		System.out.println("Akceptant id: " + user1Id);
		TermDto term;
		for (Long termId : termIds) {
			term = termService.getTermById(termId);
			System.out.println(term);
		}
		assertEquals(authorId, termService.getTermById(termIds.get(0)).getAcceptors().get(0));
	}

	private Long addUser(String name) {
		String surname = "Prijmeni" + System.currentTimeMillis();
		String password = "Heslo" + System.currentTimeMillis();
		String email = "E-mail" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();

		return userService.addUser(name, surname, email, password, description);
	}

}
