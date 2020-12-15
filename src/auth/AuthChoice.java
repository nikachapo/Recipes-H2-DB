package auth;

public class AuthChoice {

    private String field;
    private final String password;

    public AuthChoice(String field, String password) {
        this.field = field;
        this.password = password;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public String getPassword() {
        return password;
    }

    public static class EmailAuth extends AuthChoice {
        public EmailAuth(String field, String password) {
            super(field, password);
        }
    }

    public static class UserNameAuth extends AuthChoice {
        public UserNameAuth(String field, String password) {
            super(field, password);
        }
    }
}
