
package cz.timepool.bo;

/**
 *
 * @author Lukas L.
 */
public enum UserPermission {
    ACCEPT_TERM, ADD_TERM, ADD_COMMENT;
    
    public static UserPermission convertFromString(String val){
	if(val.equals("ACCEPT_TERM"))return ACCEPT_TERM;
	else if (val.equals("ADD_TERM"))return ADD_TERM;
	else if(val.equals("ADD_COMMENT")) return ADD_COMMENT;
	return null;
    }
}
