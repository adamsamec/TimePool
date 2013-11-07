
package cz.timepool.service;

import cz.timepool.bo.Term;
import cz.timepool.bo.User;
import cz.timepool.dto.TermDto;
import cz.timepool.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lukas Lowinger
 */
@Component
class TermServiceImpl extends AbstractDataAccessService implements TermService{

    @Override
    public Long addTermToEvent(Date suggestedDate, String status, String description, Date creationDate, Long author) {
        Term term = new Term();
        term.setCreationDate(creationDate);
        term.setSuggestedDate(suggestedDate);
        term.setDescription(description);
        term.setStatus(status);
        term.setAuthor(genericDao.loadById(author, User.class));
        return genericDao.saveOrUpdate(term).getId();
    }
    
    @Override
    public void deleteTermById(Long idTerm) {
        genericDao.removeById(idTerm, Term.class);
    }

    @Override
    public void editTermById(TermDto changedTerm, Long idTerm) {
        Term term = new Term();
        term.setCreationDate(changedTerm.getCreationDate());
        term.setSuggestedDate(changedTerm.getSuggestedDate());
        term.setDescription(changedTerm.getDescription());
        term.setStatus(changedTerm.getStatus());
        term.setAuthor(genericDao.loadById(changedTerm.getAuthor(), User.class));
        //term.setParticipants(DtoTransformerHelper.getIdentifiers(changedTerm.get));
        genericDao.saveOrUpdate(term);
    }

    @Override
    public List<TermDto> getTermsByEventId(Long idEvent) {
        List<TermDto> termsDto = new ArrayList<TermDto>();
        List<Term> terms = genericDao.getByProperty("event_id", idEvent, Term.class);
        for (Term term : terms) {
            termsDto.add(new TermDto(term.getSuggestedDate(), term.getStatus(), term.getDescription(), term.getCreationDate(), term.getAuthor().getId(), DtoTransformerHelper.getIdentifiers(term.getParticipants()), DtoTransformerHelper.getIdentifiers(term.getComments())));
        }
        return termsDto;
    }

    @Override
    public void changeTermStatusById(String status, Long termId) {
        Term term = genericDao.loadById(termId, Term.class);
        term.setStatus(status);
        genericDao.saveOrUpdate(term);
    }

    @Override
    public void addParticipantToTermById(Long idParticipant, Long idTerm) {
        User participant = genericDao.loadById(idParticipant, User.class);
        Term term = genericDao.loadById(idTerm, Term.class);
        term.addParticipant(participant);
        genericDao.saveOrUpdate(term);
    }
   
}