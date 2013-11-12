package cz.timepool.dao;

import cz.timepool.bo.AbstractBusinessObject;
import cz.timepool.bo.Event;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
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
	public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz) {
		return getEntityManager().find(clazz, id);
	}

	@Override
	public <ENTITY> ENTITY loadById(long id, Class<ENTITY> clazz) {
		return (ENTITY) ((Session) getEntityManager().getDelegate()).load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
		return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
	}

	@SuppressWarnings("unchecked")
	public <ENTITY> List<ENTITY> getAllOrdered(Class<ENTITY> clazz, String order) {
		return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e ORDER BY " + order).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <ENTITY> List<ENTITY> getAllByProperty(String property, Object value, Class<ENTITY> clazz) {
		String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
		return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
	}

	public <ENTITY> ENTITY getSingleByProperty(String property, Object value, Class<ENTITY> clazz) {
		ENTITY e;
		if (value == null) {
			e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL").getSingleResult());
		} else {
			e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value").setParameter("value", value).getSingleResult());
		}
		return e;
	}

	@Override
	public <ENTITY> List<ENTITY> getAllCreatedBetween(Date fromDate, Date toDate, Class<ENTITY> clazz) {
		return getEntityManager().createNamedQuery(clazz.getSimpleName() + ".createdBetween")
				.setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate)
				.getResultList();
	}

	public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz) {
		ENTITY e = getEntityManager().find(clazz, id);
		if (e != null) {
			System.out.println("nalezen tento " + e);
			getEntityManager().remove(e);
		} else {
			System.out.println("nenalezen");
		}
	}

	public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o) {
		getEntityManager().remove(o);
	}

	public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o) {
		if (o.getId() == null) {
			getEntityManager().persist(o);
		} else {
			getEntityManager().merge(o);
		}
		getEntityManager().flush();
		return o;
	}

	@Override
	public Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}

}
