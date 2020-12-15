package dao;

import model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private final Connection connection;

    public UserDaoImpl(Connection connection) throws SQLException {
        this.connection = connection;
        Driver driver = new org.h2.Driver();
        DriverManager.registerDriver(driver);
    }

    public UserDaoImpl() throws SQLException {
        this(DriverManager.getConnection("jdbc:h2:~/test", "sa", ""));
    }

    @Override
    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (mail,username, password) VALUES (?,?, ?)");
        preparedStatement.setString(1, user.getMail());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteUserByMail(String mail) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM user WHERE mail = " + mail);
        statement.close();
    }

    @Override
    public User getUserByMailAndPassword(String mail, String password) throws SQLException {
        return getUserFromDb(MAIL_COLUMN, mail, password);
    }

    @Override
    public User getUserByUserNameAndPassword(String userName, String password) throws SQLException {
        return getUserFromDb(USER_NAME_COLUMN, userName, password);
    }

    private <DATA> User getUserFromDb(String columnName, DATA columnData, String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE " + columnName + " = " + "'" + columnData + "'");
        if (resultSet.next()) {
            String mail = resultSet.getString(MAIL_COLUMN);
            String dbUsername = resultSet.getString(USER_NAME_COLUMN);
            String dbPassword = resultSet.getString(PASSWORD_COLUMN);
            if (!dbPassword.equals(password)) return null;
            return new User(mail, dbUsername, dbPassword);
        } else {
            return null;
        }
    }
}
