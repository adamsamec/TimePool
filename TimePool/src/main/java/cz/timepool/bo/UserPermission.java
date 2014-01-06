package cz.timepool.bo;

/**
 *
 * @author Lukas L.
 */
public enum UserPermission {

    ACCEPT_TERM("ACCEPT_TERM"),
    ADD_TERM("ADD_TERM"),
    ADD_COMMENT("ADD_COMMENT");

    private final String value;

    UserPermission(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static UserPermission fromString(String value) {
        if (value.equals("ACCEPT_TERM")) {
            return ACCEPT_TERM;
        } else if (value.equals("ADD_TERM")) {
            return ADD_TERM;
        } else if (value.equals("ADD_COMMENT")) {
            return ADD_COMMENT;
        }
        return null;
    }

}
