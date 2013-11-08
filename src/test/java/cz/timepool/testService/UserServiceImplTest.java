package cz.timepool.testService;


import cz.timepool.testService.AbstractServiceTest;
import cz.timepool.dto.UserDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TagService;
import cz.timepool.service.TagServiceImpl;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
import java.util.Date;
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
    public void testAddAndRetrieveUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();
        
        Long id = userService.addUser(name,surname,email,pass,desctiption);
        UserDto userDto = userService.getUserById(id);
        
        assertEquals(name, userDto.getName());
        assertEquals(email, userDto.getEmail());
     
        userService.addUser(name, surname, email, pass, desctiption);
    }
    @Test
    public void testAddAndRemoveUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();
        
        Long id = userService.addUser(name,surname,email,pass,desctiption);
//        assertEquals(1, userService.getAllUsers().size());
        int expected = userService.getAllUsers().size() -1;
        userService.deleteUser(id);
        assertEquals(expected, userService.getAllUsers().size());        
    }      

    @Test 
    public void complexTest(){
        Long idAuthor = userService.addUser("Autor", "Eventu", "nejaky@email.cz", "heslo", "tento uzivatel prida terminy a terminy do eventu, ktery vytvoril");
        Long idParticipant = userService.addUser("Participant", "Eventu", "nejadsfsdky@email.cz", "heslo", "tento uzivatel je participantem v termu");
        Long idEvent = eventService.addEvent(idAuthor, "Prvni event", "nevim", "popis", new Date());
        Long idTag = tagService.addTagToEvent("TAAAAAAG", idEvent);
        Long idTerm = termService.addTermToEvent(new Date(), "volny asi", "prvni termin", new Date(), idAuthor, idEvent);
        Long idTerm2 = termService.addTermToEvent(new Date(), "volny asi", "druhy zmeneny termin", new Date(), idParticipant, idEvent);
        termService.changeTermStatusById("zmena statusu !!!", idTerm);
        termService.addParticipantToTermById(idParticipant, idTerm);
        
    }
    
    private long addUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();

        return userService.addUser(name,surname,email,pass,desctiption);
    }
}
