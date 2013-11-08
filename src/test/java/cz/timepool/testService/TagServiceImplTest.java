
package cz.timepool.testService;

import cz.timepool.dto.TagDto;
import cz.timepool.service.EventService;
import cz.timepool.service.TagService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
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
    
    @Test
    public void testAddAndRetrieveAndDelete(){
        Long idEvent = eventService.addEvent(new Long(3), "Koupani ve studene vode ", "Rybnik v Rusku  ","Bude to narez, prijdte vsichni.",new Date());
        tagService.addTagToEvent("ZIMA", idEvent);
        List<TagDto> tags = tagService.getAllTags();
    }
}
