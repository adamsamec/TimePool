
package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.Tag;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class TagServiceImpl extends AbstractDataAccessService implements TagService{

    @Override
    public Long addTagToEvent(String text, Long event) {
        Tag t = new Tag();
        t.setText(text);
        Event e = genericDao.loadById(event, Event.class);
        t.setEvent(e);
        return genericDao.saveOrUpdate(t).getId();
    }

}
