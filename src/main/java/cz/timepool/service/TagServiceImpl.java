
package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.Tag;
import cz.timepool.dto.TagDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas L.
 */
@Component
public class TagServiceImpl extends AbstractDataAccessService implements TagService{

    @Override
    public Long addTagToEvent(String text, Long event) {
        Tag t = new Tag();
        t.setText(text);
        Event e = genericDao.loadById(event, Event.class);
        e.addTag(t);
        return genericDao.saveOrUpdate(t).getId();
    }

    @Override
    public List<TagDto> getAllTags() {
        List<TagDto> tagDtos = new ArrayList<TagDto>();
        List<Tag> tags = genericDao.getAll(Tag.class);
        for (Tag tag : tags) {
            tagDtos.add(new TagDto(tag.getId(), DtoTransformerHelper.getIdentifiers(tag.getEvents()), tag.getText()));
        }
        return tagDtos;
    }

    @Override
    public void deleteTag(Long tag) {
        genericDao.removeById(tag, Tag.class);
    }

}
