
package cz.timepool.testService;

import cz.timepool.dto.EventDto;
import cz.timepool.service.EventService;
import cz.timepool.service.UserService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas L.
 */
public class EventServiceImplTest extends AbstractServiceTest{
    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testAddEventAndRetrieveAndDelete(){
        Long idAuthor = userService.addUser("Jmeno", "Prijmeni", "imejl", "passss", "poppopopois");
        List<EventDto> events = eventService.getAllEvents();
        int before = events.size();
        
        Long idEvent = eventService.addEvent(idAuthor, "nazev", "lokace", "popis", new Date());
        events = eventService.getAllEvents();
        
        assertEquals(before+1, events.size());
        eventService.deleteEventById(idEvent);
        
        events = eventService.getAllEvents();
        assertEquals(before, events.size());
    }
}
