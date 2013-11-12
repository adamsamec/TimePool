package cz.timepool.dao;

import cz.timepool.bo.AbstractBusinessObject;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Lukas Lowinger
 */
public interface GenericDao {

	public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz);

	public <ENTITY> ENTITY loadById(long id, Class<ENTITY> clazz);

	public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz);

	public <ENTITY> List<ENTITY> getAllOrdered(Class<ENTITY> clazz, String order);

	public <ENTITY> List<ENTITY> getAllByProperty(String property, Object value, Class<ENTITY> clazz);

	public <ENTITY> ENTITY getSingleByProperty(String property, Object value, Class<ENTITY> clazz);

	public <ENTITY> List<ENTITY> getAllCreatedBetween(Date fromDate, Date toDate, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o);

	public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o);

	public Session getSession();

}
