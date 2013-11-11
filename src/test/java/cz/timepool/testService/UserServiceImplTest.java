package cz.timepool.testService;

import cz.timepool.testService.AbstractServiceTest;
import cz.timepool.dto.UserDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TagService;
import cz.timepool.service.TagServiceImpl;
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
	public void testAddAndRetrieveUser() {
		String name = "Budu" + System.currentTimeMillis();
		String surname = "Navracen" + System.currentTimeMillis();
		String pass = "passwd" + System.currentTimeMillis();
		String email = "email" + System.currentTimeMillis();
		String desctiption = "desc" + System.currentTimeMillis();

		Long id = userService.addUser(name, surname, email, pass, desctiption);
		UserDto userDto = userService.getUserById(id);
		assertEquals(name, userDto.getName());
		assertEquals(email, userDto.getEmail());
	}

	@Test
	public void testAddAndRemoveUser() {
		String name = "Budu" + System.currentTimeMillis();
		String surname = "Odstranen" + System.currentTimeMillis();
		String pass = "Moje heslo" + System.currentTimeMillis();
		String email = "email" + System.currentTimeMillis();
		String desctiption = "desc" + System.currentTimeMillis();

		int beforeCount = userService.getAllUsers().size();
		Long id = userService.addUser(name, surname, email, pass, desctiption);
        assertEquals(beforeCount + 1, userService.getAllUsers().size());
		
		userService.deleteUser(id);
		assertEquals(beforeCount, userService.getAllUsers().size());
	}

	@Test
	public void testAddAndRetrieveUsersOrdered() {
		addUser("Jarda");
		addUser("Vincent (ten posledni)");
		addUser("Pepa");
		addUser("Alfred");
		
		List<UserDto> users = userService.getAllUsersOrderByName();
		UserDto lastUser = users.get(users.size() - 1);
		System.out.println("TEST - Vypis uzivatelu serazeny podle jmena:");
		for (UserDto userDto : users) {
			System.out.println(userDto.getName());
		}
		assertEquals("Vincent (ten posledni)", lastUser.getName());
	}

	@Test
	public void complexTest() {
		Long idAuthor = userService.addUser("Autor", "Eventu", "nejaky@email.cz", "heslo", "tento uzivatel prida terminy a terminy do eventu, ktery vytvoril");
		Long idParticipant = userService.addUser("Participant", "Eventu", "nejadsfsdky@email.cz", "heslo", "tento uzivatel je participantem v termu");
		Long idEvent = eventService.addEvent(idAuthor, "Prvni event", "nevim", "popis", new Date());
		Long idTag = tagService.addTagToEvent("TAAAAAAG", idEvent);
		Long idTerm = termService.addTermToEvent(new Date(), "volny asi", "prvni termin", new Date(), idAuthor, idEvent);
		Long idTerm2 = termService.addTermToEvent(new Date(), "volny asi", "druhy zmeneny termin", new Date(), idParticipant, idEvent);
		termService.changeTermStatusById("zmena statusu !!!", idTerm);
		termService.addParticipantToTermById(idParticipant, idTerm);

	}

	private long addUser(String name) {
		String surname = "Prijmeni" + System.currentTimeMillis();
		String pass = "Heslo" + System.currentTimeMillis();
		String email = "E-mail" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();

		return userService.addUser(name, surname, email, pass, description);
	}
}
