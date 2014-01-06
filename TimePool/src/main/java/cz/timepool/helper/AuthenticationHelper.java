package cz.timepool.helper;

import java.security.MessageDigest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Adam Samec <samecada@fel.cvut.cz>
 * @link   fel.cvut.cz
 */
public class AuthenticationHelper {

    private static final String USER_PASSWORD_SALT = "hotento";

    private static final Logger log = Logger.getLogger(AuthenticationHelper.class);

    public static String userPasswordHash(String password, String username) {
        String hash = null;
        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            md.update(USER_PASSWORD_SALT.getBytes("UTF-8"));
//            md.update(username.getBytes("UTF-8"));
//            byte[] byteHash = md.digest(password.getBytes("UTF-8"));
//            hash = new String(byteHash, "UTF-8");
            hash = DigestUtils.sha256Hex(password); 
        } catch (Exception ex) {
            log.fatal(ex.getMessage(), ex);
        }
        return hash;
    }

}
