
package cz.timepool.service;

import cz.timepool.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lukas Lowinger
 */
public abstract class AbstractDataAccessService {
    @Autowired
    protected GenericDao genericDao;
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }    
}
