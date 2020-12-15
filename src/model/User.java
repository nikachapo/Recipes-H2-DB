package model;

public class User {

    private String mail;
    private String username;
    private String password;

    public User(String mail, String username, String password) {
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "mail=" + mail +
                ", username='" + username + '\'' +
                '}';
    }
}
