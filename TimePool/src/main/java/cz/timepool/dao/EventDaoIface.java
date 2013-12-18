package cz.timepool.dao;

import cz.timepool.bo.Tag;
import java.util.List;

/**
 *
 * @author   Adam Samec <samecada@fel.cvut.cz>
 * @link     fel.cvut.cz
 */
public interface EventDaoIface {

    public <ENTITY> List<ENTITY> getAllEventsWithTags(List<Tag> tags);

}
