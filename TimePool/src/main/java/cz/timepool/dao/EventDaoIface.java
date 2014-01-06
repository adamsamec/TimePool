package cz.timepool.dao;

import cz.timepool.bo.Event;
import cz.timepool.bo.Tag;
import java.util.List;

/**
 *
 * @author   Adam Samec <samecada@fel.cvut.cz>
 * @link     fel.cvut.cz
 */
public interface EventDaoIface {

    public List<Event> getAllEventsWithTags(List<Tag> tags);

}
