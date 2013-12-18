package cz.timepool.dao;

import org.springframework.stereotype.Component;

/**
 *
 * @author Adam Samec
 */
@Component("timepoolDao")
public class TimepoolDao extends HibernateJpaDao implements TimepoolDaoIface {
    
}
