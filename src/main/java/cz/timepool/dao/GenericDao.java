
package cz.timepool.dao;

import cz.timepool.bo.AbstractBusinessObject;
import java.util.List;

/**
 *
 * @author Lukas Lowinger
 */
public interface GenericDao {

    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz);
    
    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz);
    
    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o);
    
    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o);
    
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz);
    
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz);
    
}
