
package cz.timepool.dao;

import cz.timepool.bo.AbstractBusinessObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas Lowinger
 */
@Component("hibernateJpaDao")
public class HibernateJpaDao implements GenericDao {

    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz) {
        ENTITY e = getEntityManager().find(clazz, id);
        if (e != null) {
            getEntityManager().remove(e);
        }
    }

    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o) {
        getEntityManager().remove(o);
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz) {
        return getEntityManager().find(clazz, id);
    }

    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o) {
        if (o.getId() == null) {
            getEntityManager().persist(o);
        } else {
            getEntityManager().merge(o);
        }
        return o;
    }
    
    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz) {
        ENTITY e;
        if (value == null) {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL" ).getSingleResult());
        } else {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value" ).setParameter("value", value).getSingleResult());
        }
        return e;
    }
}

