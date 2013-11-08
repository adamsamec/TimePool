
package cz.timepool.testService;

import cz.timepool.service.CommentService;
import cz.timepool.service.EventService;
import cz.timepool.service.TermService;
import cz.timepool.service.UserService;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 *
 * @author Lukas L.
 */
public class CommentServiceImplTest  extends AbstractServiceTest{
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TermService termService;
    
    @Autowired
    private EventService eventService;
    
    @Test
    public void testAddCommentToTermDeleteAndRetrieve(){
        Long idUser = userService.addUser("dfsf"+System.currentTimeMillis(), "dsfsd", "fdsfsd", "dfsfsd", "dgjgdflgjdfkgjld");
        Long idEvent = eventService.addEvent(idUser,"dsfsdf","dsfsf","dsfsdf",new Date());
        
        Long idTerm = termService.addTermToEvent(new Date(),"statusss","dsfsdfs",new Date(),idUser,idEvent);
        commentService.addCommentToTerm("TEEEEEEXT KOMENTARE",idUser,idTerm);
    }
    
    

    
    
}
