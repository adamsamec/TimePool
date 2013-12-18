package cz.timepool.dao;

import cz.timepool.bo.Tag;
import java.util.List;

/**
 *
 * @author Adam Samec <adam.smec@gmail.com>
 */
public interface EventDaoIface {

    public <ENTITY> List<ENTITY> getAllEventsWithTags(List<Tag> tags);

}
