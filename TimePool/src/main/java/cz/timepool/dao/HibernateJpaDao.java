package cz.timepool.dao;

import cz.timepool.bo.AbstractBusinessObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas Lowinger
 */
@Component("genericDao")
public class HibernateJpaDao implements GenericDao {
    
    private static final Logger log = Logger.getLogger(HibernateJpaDao.class);

	@Autowired
	protected EntityManagerFactory entityManagerfactory;

	protected EntityManager getEntityManager() {
		return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
	}

	@SuppressWarnings("unchecked")
	public <ENTITY extends AbstractBusinessObject> ENTITY getById(Long id, Class<ENTITY> clazz) {
		return getEntityManager().find(clazz, id);
	}

	@Override
	public <ENTITY extends AbstractBusinessObject> ENTITY loadById(long id, Class<ENTITY> clazz) {
		return (ENTITY) ((Session) getEntityManager().getDelegate()).load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAll(Class<ENTITY> clazz) {
		return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
	}

	@SuppressWarnings("unchecked")
	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAllOrdered(String order, Class<ENTITY> clazz) {
		return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e ORDER BY " + order).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAllByProperty(String property, Object value, Class<ENTITY> clazz) {
		String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
		return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
	}

	public <ENTITY extends AbstractBusinessObject> ENTITY getSingleByProperty(String property, Object value, Class<ENTITY> clazz) {
		ENTITY e;
		if (value == null) {
			e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL").getSingleResult());
		} else {
			e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value").setParameter("value", value).getSingleResult());
		}
		return e;
	}

	public <ENTITY extends AbstractBusinessObject> List<ENTITY> getAllBetween(String columnName, Object fromValue, Object toValue, Class<ENTITY> clazz) {
		Criteria c = getSession().createCriteria(clazz);
		c.add(Expression.ge(columnName, fromValue)).add(Expression.le(columnName, toValue));
		return c.list();
	}

	public <ENTITY extends AbstractBusinessObject> ENTITY save(ENTITY o) {
		if (o.getId() == null) {
			getEntityManager().persist(o);
		} else {
			getEntityManager().merge(o);
		}
		getEntityManager().flush();
		return o;
	}

	public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o) {
		getEntityManager().remove(o);
	}

	public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz) {
		ENTITY e = getEntityManager().find(clazz, id);
		if (e != null) {
			log.debug("Entity found: " + e);
			getEntityManager().remove(e);
		} else {
			log.debug("Entity not found");
		}
	}

	@Override
	public Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}

    @Override
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
