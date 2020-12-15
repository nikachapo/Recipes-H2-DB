package recipes_app;

import auth.AuthChoice;
import auth.IAuth;
import com.sun.istack.internal.Nullable;
import dao.RecipeDao;
import dao.UserDao;
import io.InputOutput;
import model.Recipe;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class RecipeApp implements RecipeAppTemplate {

    private final IAuth authenticator;
    private final InputOutput io;
    private final UserDao userDao;
    private final RecipeDao recipeDao;
    private final RecipeCommands recipeCommands;

    @Nullable
    private User user = null;

    public RecipeApp(IAuth authenticator, InputOutput io, UserDao userDao, RecipeDao recipeDao) {
        this.authenticator = authenticator;
        this.io = io;
        this.userDao = userDao;
        this.recipeDao = recipeDao;
        recipeCommands =  new RecipeCommands(this, io);
    }


    @Override
    public void start() {
        recipeCommands.chooseOperation(true);
    }

    @Override
    public void authUser(AuthChoice authChoice) {
        user = authenticator.authenticate(authChoice);
        if (user == null){
            io.showText("User doesn't exist");
            finish();
        } else {
            io.showText("Welcome " + user.getUsername());
            recipeCommands.chooseOperation(false);
        }
    }

    @Override
    public void registerUser(User user) {
        try {
            userDao.addUser(user);
            this.user = user;
        } catch (SQLException e) {
            io.showText(e.getMessage());
        }
    }

    @Override
    public void finish() {
        io.showText("Good Bye!!!");
    }

    @Override
    public List<Recipe> getRecipes() {
        try {
            return recipeDao.getRecipes(user.getMail());
        } catch (SQLException throwables) {
            io.showText(throwables.getMessage());
        }
        return null;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        try {
            recipeDao.addRecipe(recipe);
            io.showText(recipe.getTitle() + " added!");
        } catch (SQLException throwables) {
            io.showText("Server error");
        }
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        try {
            recipeDao.deleteRecipeByName(recipe.getTitle());
            io.showText(recipe.getTitle() + " deleted!");
        } catch (SQLException throwables) {
            io.showText(throwables.getMessage());
        }
    }

    @Nullable
    public User getUser() {
        return user;
    }
}
