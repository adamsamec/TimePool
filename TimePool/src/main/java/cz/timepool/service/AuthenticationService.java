package cz.timepool.service;

import cz.timepool.dao.GenericDao;
import cz.timepool.helper.AuthenticationHelper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Authentication provider
 *
 * @author Pavel Micka
 */
//Configuration in applicationContext-security.xml
public class AuthenticationService extends AbstractUserDetailsAuthenticationProvider {

    private GenericDao genericDAO;

    private TransactionTemplate transactionTemplate;

    private static final Logger log = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);

    public AuthenticationService() {
        this.setUserCache(new NullUserCache());
    }

    public void setGenericDAO(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // do nothing
    }

    /**
     * @param username
     * @param upat
     * @return
     * @throws AuthenticationException
     */
    @Override
    @Transactional(readOnly = true)
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    UserDetails userDetails;
                    cz.timepool.bo.User user;
                    try {
                        log.info("Logging a user using username: " + username + ", password: " + upat.getCredentials());
                        user = genericDAO.getByPropertyUnique("email", username, cz.timepool.bo.User.class);
                    } catch (EmptyResultDataAccessException ex) {
                        throw new BadCredentialsException("Uzivatel neexistuje.");
                    }
                    String givenPassword = (String) upat.getCredentials();
                    String givenPasswordHash = AuthenticationHelper.userPasswordHash(givenPassword, username);
                    String correctPasswordHash = user.getPassword();
                    if (!givenPasswordHash.equals(correctPasswordHash)) {
                        AuthenticationException ex = new BadCredentialsException("Nespravne heslo.");
                        throw ex;
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                        switch (user.getUserRole()) {
                            case ADMIN:
                                auths.add(new SimpleGrantedAuthority("ADMIN"));
                                break;
                            case USER:
                                auths.add(new SimpleGrantedAuthority("USER"));
                                break;
                        }
                        userDetails = new User(user.getEmail(), user.getPassword(), auths);
                    }
                    return userDetails;
                } catch (AuthenticationException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception ex) {
                    log.error("Error occured during retrieveUser call", ex);
                    status.setRollbackOnly();
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
