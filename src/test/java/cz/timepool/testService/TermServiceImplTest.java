
package cz.timepool.testService;

import cz.timepool.dto.TermDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas L.
 */
public class TermServiceImplTest extends AbstractServiceTest{
    
    @Autowired
    private TermService termService;
    
    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserService userService;
    
//    @Test
//    public void testAddTermAndChangeStatusInEventAndRetrieve(){
//        Long idAuthor = userService.addUser("Lukas", "Lowinger", "mejl", "pass", "popis");
//        Long idEvent = addEvent(idAuthor);
//        Long idTerm = termService.addTermToEvent(new Date(),"Status pridavani","pridavani ", new Date(),idAuthor,idEvent);
//        String status = "ZMENA STATUSU";
//        termService.changeTermStatusById(status, idTerm);
//       
//        List<TermDto> list = termService.getTermsByEventId(idEvent);
//        for (TermDto termDto : list) {
//            assertEquals( status,termDto.getStatus());
//        }
//    }
    
    @Test
    public void testAddTermAndDelete(){
        Long idAuthor = userService.addUser("Lukas", "Druhej", "mejl", "pass", "popis");
        Long idEvent = addEvent(idAuthor);
        System.out.println("id event : "+idEvent);
        Long idTerm = termService.addTermToEvent(new Date(), "statusek", "popisek", new Date(), idAuthor, idEvent);
        System.out.println("id term : "+idTerm);
        List<TermDto> terms = termService.getTermsByEventId(idEvent);
        int expected = terms.size();
        for (TermDto termDto : terms) {
            System.out.println(termDto);
        }
        System.out.println("velikost : "+expected);
        //assertEquals(1, expected);
        termService.deleteTermById(idTerm);
        
          List<TermDto> termss = termService.getTermsByEventId(idEvent);
        for (TermDto termDto : termss) {
            System.out.println(termDto);
        }
        assertEquals(expected-1, termService.getTermsByEventId(idEvent).size());        
    }
    private Long addEvent(Long idAuthor){
        return eventService.addEvent(idAuthor, "NAZEV", "LOKACE", "POPIS", new Date());
    }
}