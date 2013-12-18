
package cz.timepool.service;

import cz.timepool.dao.TimepoolDaoIface;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas Lowinger
 */
public abstract class AbstractDataAccessService {
    @Autowired
    protected TimepoolDaoIface timepoolDao;
    
    public void setTimepoolDao(TimepoolDaoIface genericDao) {
        this.timepoolDao = genericDao;
    }

    public TimepoolDaoIface getTimepoolDao() {
        return this.timepoolDao;
    }    
}
