package cz.timepool.service;

import cz.timepool.bo.TemporalEntity;
import cz.timepool.dao.TimepoolDaoIface;
import cz.timepool.dto.TemporalEntityDto;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adam Samec <samecada@fel.cvut.cz>
 * @link     fel.cvut.cz
 */
@Transactional
public class TimepoolService implements TimepoolServiceIface {

    @Autowired
    protected TimepoolDaoIface timepoolDao;

    private static final Logger log = Logger.getLogger(TimepoolService.class);

    @Transactional(readOnly = true)
    public <ENTITY extends TemporalEntity, DTO extends TemporalEntityDto> List<DTO> getAllCreatedBetween(Object from, Object to, Class<ENTITY> entityClass, Class<DTO> dtoClass) {
        List<ENTITY> entities = this.timepoolDao.getAllBetween("creationDate", from, to, entityClass);
        List<DTO> dtos = new ArrayList<DTO>();
        for (ENTITY entity : entities) {
            try {
                Class<?> clazz = Class.forName(dtoClass.getName());
                Constructor<?> constructor = clazz.getConstructor(entityClass);
                DTO dto = (DTO) constructor.newInstance(entity);
                dtos.add(dto);
            } catch (ReflectiveOperationException ex) {
                log.fatal(ex.getMessage(), ex);
            }
        }
        return dtos;
    }

}
