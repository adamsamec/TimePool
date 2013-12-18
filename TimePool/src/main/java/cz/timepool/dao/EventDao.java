package cz.timepool.dao;

import cz.timepool.bo.Tag;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author   Adam Samec <samecada@fel.cvut.cz>
 * @link     fel.cvut.cz
 */
@Component("eventDao")
public class EventDao extends HibernateJpaDao implements EventDaoIface {

	public <ENTITY> List<ENTITY> getAllEventsWithTags(List<Tag> tags) {
		return getEntityManager().createNamedQuery("Event.getAllWithTags")
				.setParameter("tags", tags)
				.getResultList();
	}

}
