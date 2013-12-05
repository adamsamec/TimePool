package cz.timepool.dao;

import cz.timepool.bo.AbstractBusinessObject;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Lukas Lowinger
 * @todo Zvazit nahrazeni DAO Dispatcherem.
 */
public interface GenericDao {

	public <ENTITY extends AbstractBusinessObject> ENTITY getById(Long id, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> ENTITY loadById(long id, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAll(Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAllOrdered(String order, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAllByProperty(String property, Object value, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> ENTITY getSingleByProperty(String property, Object value, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAllBetween(String columnName, Object fromValue, Object toValue, Class<ENTITY> clazz);

	public <ENTITY extends AbstractBusinessObject> ENTITY save(ENTITY o);

	public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o);

	public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz);

	public Session getSession();

}
