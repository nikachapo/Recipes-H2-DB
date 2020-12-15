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

        Authenticator authenticator = new Authenticator(new UserDaoImpl());
        InputOutput io = new CommandLineIO();
        UserDao dao = new UserDaoImpl();
        RecipeDao recipeDao = new RecipeDaoImpl();
        new RecipeApp(authenticator, io, dao, recipeDao).start();

    }

}
