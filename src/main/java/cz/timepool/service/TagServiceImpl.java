
package cz.timepool.service;

import cz.timepool.bo.Event;
import cz.timepool.bo.Tag;
import cz.timepool.dto.TagDto;
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
        t.setEvent(e);
        return genericDao.saveOrUpdate(t).getId();
    }

    @Override
    public List<TagDto> getAllTags() {
        List<TagDto> tagDtos = new ArrayList<TagDto>();
        List<Tag> tags = genericDao.getAll(Tag.class);
        for (Tag tag : tags) {
            tagDtos.add(new TagDto(tag.getId(), tag.getEvent().getId(), tag.getText()));
        }
        return tagDtos;
    }

    @Override
    public void deleteTagFromEvent(Long tag) {
        genericDao.
    }

}
