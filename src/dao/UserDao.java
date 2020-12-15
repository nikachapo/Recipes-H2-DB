package dao;

import com.sun.istack.internal.Nullable;
import model.User;

import java.sql.SQLException;

public interface UserDao {

    String MAIL_COLUMN = "mail";
    String USER_NAME_COLUMN = "username";
    String PASSWORD_COLUMN = "password";


    void addUser(User user) throws SQLException;

    void deleteUserByMail(String mail) throws SQLException;

    @Nullable
    User getUserByMailAndPassword(String mail, String password) throws SQLException;

    @Nullable
    User getUserByUserNameAndPassword(String userName, String password) throws SQLException;


}
