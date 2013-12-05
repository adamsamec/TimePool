package cz.timepool.service;

import cz.timepool.dto.TermDto;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lukas Lowinger
 */
@Transactional
public interface TermService {

    //TODO: addTermToEvent, deleteTermById, editTermById, getTermByEventId, changeTermStatusById, addParticipantToTermById 
    @Transactional(readOnly = true)
    public TermDto getTermById(Long termId);

    @Transactional(readOnly = true)
    public List<TermDto> getTermsByEventId(Long eventId);

    public Long addTermToEvent(Date termDate, String status, String description, Date creationDate, Long authorId, Long eventId);

    public void deleteTermById(Long termId);

    public void editTermById(TermDto editedTerm, Long termId);

    public void changeTermStatusById(String status, Long termId);

    public void addAcceptorToTermById(Long acceptorId, Long idTerm);

}
