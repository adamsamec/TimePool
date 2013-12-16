/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.timepool.service;

import cz.timepool.bo.UserRole;
import cz.timepool.dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
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

    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);

    private GenericDao genericDAO;

    private TransactionTemplate transactionTemplate;

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
                        System.out.println("username " + username + "pass " + upat.getCredentials());
                        user = genericDAO.getByPropertyUnique("email", username, cz.timepool.bo.User.class);
                    } catch (EmptyResultDataAccessException erdaex) {
                        throw new BadCredentialsException("Uzivatel neexistuje");
                    }
                    String password = (String) upat.getCredentials();
                    if (user == null || !user.getPassword().equals(password)) {
                        AuthenticationException e = new BadCredentialsException("Neplatne uzivatelske udaje");
                        throw e;
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                        if (user.getUserRole().equals(UserRole.ADMIN)) {
                            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        }
                        userDetails = new User(user.getEmail(), user.getPassword(), auths);
                    }
                    return userDetails;
                } catch (AuthenticationException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception ex) {
                    LOG.error("Error occured during retrieveUser call", ex);
                    status.setRollbackOnly();
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
