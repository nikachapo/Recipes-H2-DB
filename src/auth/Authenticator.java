package auth;

import dao.UserDao;
import model.User;

import java.sql.SQLException;

public class Authenticator implements IAuth {

    private final UserDao userDao;

    public Authenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User authenticate(AuthChoice authChoice) {

        if (authChoice instanceof AuthChoice.EmailAuth) {
            try {
                return userDao.getUserByMailAndPassword(authChoice.getField(), authChoice.getPassword());
            } catch (SQLException e) {
                return null;
            }
        } else {
            try {
                return userDao.getUserByUserNameAndPassword(authChoice.getField(), authChoice.getPassword());
            } catch (SQLException e) {
                return null;
            }
        }
    }

}