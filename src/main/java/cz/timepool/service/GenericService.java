package cz.timepool.service;

import cz.timepool.bo.AbstractBusinessObject;
import cz.timepool.dto.TemporalEntityDto;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adam Samec <adam.smec@gmail.com>
 */

@Transactional
public abstract class GenericService extends AbstractDataAccessService {

    @Transactional(readOnly = true)
	protected <ENTITY extends AbstractBusinessObject, DTO extends TemporalEntityDto> List<DTO> getAllCreatedBetween(Object from, Object to, Class<ENTITY> entityClass, Class<DTO> dtoClass) {
		List<ENTITY> entities = this.timepoolDao.getAllBetween("creationDate", from, to, entityClass);
		List<DTO> dtos = new ArrayList<DTO>();
		for (ENTITY entity : entities) {
			try {
				Class<?> clazz = Class.forName(dtoClass.getName());
				Constructor<?> constructor = clazz.getConstructor(entityClass);
				DTO dto = (DTO) constructor.newInstance(entity);
				dtos.add(dto);
			} catch (ReflectiveOperationException ex) {
				Logger.getLogger(GenericService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return dtos;
	}

}
