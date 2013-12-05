package cz.timepool.service;

import cz.timepool.dto.TagDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
@Transactional
public interface TagService {

    public Long addTagToEvent(String text, Long event);

    //public Long addTag(String text);
    @Transactional(readOnly = true)
    public List<TagDto> getAllTags();

    public void deleteTag(Long tag);

}
