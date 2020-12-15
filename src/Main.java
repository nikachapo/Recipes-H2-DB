import auth.Authenticator;
import dao.RecipeDao;
import dao.RecipeDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import io.CommandLineIO;
import io.InputOutput;
import recipes_app.RecipeApp;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoImpl();
        RecipeDao recipeDao = new RecipeDaoImpl();

        InputOutput io = new CommandLineIO();

        Authenticator authenticator = new Authenticator(userDao);

        new RecipeApp(authenticator, io, userDao, recipeDao).start();

    }

}
