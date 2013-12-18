package cz.timepool.testService;

import cz.timepool.bo.StatusEnum;
import cz.timepool.bo.UserRole;
import cz.timepool.dto.EventDto;
import cz.timepool.dto.TermDto;
import cz.timepool.dto.UserDto;
import cz.timepool.service.EventsServiceIface;
import cz.timepool.service.UsersServiceIface;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas Lowinger
 */
public class UserServiceImplTest extends AbstractServiceTest {

	@Autowired
	private UsersServiceIface usersService;

	@Autowired
	private EventsServiceIface eventsService;

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

		Long userId = usersService.addUser(name, surname, email, password, description);
		UserDto user = usersService.getUserById(userId);
		assertEquals(name, user.getName());
		assertEquals(email, user.getEmail());
		
		UserDto ja = new UserDto(usersService.addUser("lukas", "lowinger", "lukasinko@gmail.com", "heslo", "desc"), "lukasinko@gmail.com", "lukas", "lowinger", "heslo", "desc", UserRole.ADMIN, new Random().nextInt(), null, null, null, null, null, null);
		usersService.editUser(ja);
		
	}

	@Test
	public void testAddRetrieveUsersOrdered() {
		String expectedUserName = "Vincent (prisel druhy, ale je posledni, to jsou veci!)";
		Long user1Id = this.addUser("Jarda");
		Long user2Id = this.addUser(expectedUserName);
		Long user3Id = this.addUser("Pepa");
		Long user4Id = this.addUser("Alfred");

		List<UserDto> users = usersService.getAllUsersOrderedByName();
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
		int beforeUsersCount = usersService.getAllUsers().size();

		Long userId = usersService.addUser(name, surname, email, password, description);
		assertEquals(beforeUsersCount + 1, usersService.getAllUsers().size());

		usersService.deleteUser(userId);
		assertEquals(beforeUsersCount, usersService.getAllUsers().size());
	}

	@Test
	public void testAddUserAddEventsRemoveUser() {
		String name = "Budu" + System.currentTimeMillis();
		String surname = "Odstranen i s mymi akcemi" + System.currentTimeMillis();
		String password = "Heslo" + System.currentTimeMillis();
		String email = "emauj@nojo.no" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();
		// TODO: Vytvaret a predavat DTO misto property
		Long authorId = usersService.addUser(name, surname, email, password, description);
		Long event1Id = eventsService.addEvent(authorId, "Akce 1", "Misto", "Vsude akce!", new Date(100000));
		Long event2Id = eventsService.addEvent(authorId, "Akce 2", "Misto", "Jenom akce!", new Date(200000));
		Long event3Id = eventsService.addEvent(authorId, "Akce 3", "Misto", "Chci akci!", new Date(300000));
		int beforeEventsCount = eventsService.getAllEvents().size();

		usersService.deleteUser(authorId);
		int afterEventsCount = eventsService.getAllEvents().size();
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
		Long authorId = usersService.addUser("Autor", "Eventu", "nejaky@email.cz", "heslo", "Tento uzivatel to ma vse pod palcem a nakonec se prida do terminu.");
		Long user1Id = usersService.addUser("Akceptant", "Eventu", "nejadsfsdky@email.cz", "heslo", "Tento uzivatel navhrne nejake terminy a do jednoho se take prida.");
		// (1):
		Long eventId = eventsService.addEvent(authorId, "Nova suprova akce", "Kdesi", "Popis akce.", new Date());
		// (2):
		Long term2Id = eventsService.addTermToEvent(new Date(1388322514000L), StatusEnum.PLNY, "Druhy nejblizsi termin", new Date(), user1Id, eventId);
		Long term1Id = eventsService.addTermToEvent(new Date(1398322514000L), StatusEnum.PLNY, "Treti nejblizsi termin", new Date(), user1Id, eventId);
		Long term3Id = eventsService.addTermToEvent(new Date(1386522514000L), StatusEnum.PLNY, "Prvni nejblizsi termin", new Date(), user1Id, eventId);
		// (3):
		eventsService.addAcceptorToTermById(user1Id, term2Id);
		// (4):
		eventsService.changeTermStatusById(StatusEnum.PLNY, term1Id);
		// (5):
		eventsService.addAcceptorToTermById(authorId, term3Id);
		// (6):
		Long tagId = eventsService.addTagToEvent("Limited edition akce, c'mon!", eventId);
		
		EventDto eventDto = eventsService.getEventById(eventId);
		List<Long> termIds = eventDto.getTerms();
		System.out.println(">>> TEST UseCase1 >>> Terminy akce " + eventDto + ":");
		System.out.println("Autor id: " + authorId);
		System.out.println("Akceptant id: " + user1Id);
		TermDto term;
		for (Long termId : termIds) {
			term = eventsService.getTermById(termId);
			System.out.println(term);
		}
		assertEquals(authorId, eventsService.getTermById(termIds.get(0)).getAcceptors().get(0));
	}
	
	@Test
	public void testManyToMany(){
	    Long authorId = addUser("NEKDO");
	    Long user2Id = addUser("DALSI");
	    Long eventId = eventsService.addEvent(authorId, "Nova suprova akce", "Kdesi", "Popis akce.", new Date());
	    Long term2Id = eventsService.addTermToEvent(new Date(1388322514000L), StatusEnum.PLNY, "Druhy nejblizsi termin", new Date(), authorId, eventId);
	    eventsService.addAcceptorToTermById(authorId, term2Id);
	    eventsService.addAcceptorToTermById(user2Id, term2Id);
	    List<Long> users = eventsService.getTermById(term2Id).getAcceptors();
	    int user = -1;
	    for (Long u : users) {
		if(user == 0){
		if(u!=authorId){
		    fail();
		}
		}
		if(user == 1){
		if(u!=user2Id){
		    fail();
		}    
		}
		
	    }
	    
	}

	private Long addUser(String name) {
		String surname = "Prijmeni" + System.currentTimeMillis();
		String password = "Heslo" + System.currentTimeMillis();
		String email = "E-mail" + System.currentTimeMillis();
		String description = "Popis" + System.currentTimeMillis();

		return usersService.addUser(name, surname, email, password, description);
	}

}
