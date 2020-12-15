package recipes_app;

import auth.AuthChoice;
import command.Command;
import io.InputOutput;
import model.Recipe;
import model.User;

import java.util.Arrays;
import java.util.List;

public class RecipeCommands {

    private RecipeApp recipeApp;
    private InputOutput io;

    private static Command lastExecutedCommand = null;

    public RecipeCommands(RecipeApp recipeApp, InputOutput io) {
        this.recipeApp = recipeApp;
        this.io = io;
    }

    public final Command LOGIN = new Command("Log In", () -> {
        int choice = io.chooseOperation(Arrays.asList("email", "user name"), "Login with :");
        if (choice == 0) {
            recipeApp.authUser(
                    new AuthChoice.EmailAuth(io.getInput("Enter Email:"), io.getInput("Enter Password:")));
        } else {
            recipeApp.authUser(
                    new AuthChoice.UserNameAuth(io.getInput("Enter UserName:"), io.getInput("Enter Password:")));
        }
    });

    public final Command REGISTER = new Command("Register", () -> {
        String email = io.getInput("Enter Email");
        String username = io.getInput("Enter username:");
        String password = io.getInput("Enter Password");
        recipeApp.registerUser(new User(email, username, password));
        io.showText(username + " Registered successfully!");
        showBaseCommands();
    });

    public final Command ADD_RECIPE = new Command("Add Recipe", () -> {
        Recipe recipe = new Recipe(recipeApp.getUser().getMail(),
                io.getInput("Enter Recipe title:"), io.getDouble("Enter making time:"));

        int operation = 0;

        while (operation != 1) {
            recipe.addIngredient(io.getInput("Enter Ingredient:"));
            operation = io.chooseOperation(Arrays.asList("Add ingredient", "No more ingredients"), "");
        }

        recipeApp.addRecipe(recipe);

        showBaseCommands();
    });

    public final Command GET_RECIPES = new Command("Get Recipes", () -> {
        List<Recipe> recipes = recipeApp.getRecipes();
        if (recipes.size() == 0) {
            io.showText("No recipes added yet.");
            showBaseCommands();
            return;
        }
        io.printList(recipes);
        showBaseCommands();
    });

    public final Command DELETE_RECIPE = new Command("Delete Recipe", () -> {
        Recipe recipe;
        if (lastExecutedCommand == GET_RECIPES) recipe = recipeApp.getRecipes().get(io.getInt("Enter index:"));
        else recipe = recipeApp.getRecipes().get(io.chooseOperation(recipeApp.getRecipes(), "Enter Index"));
        recipeApp.deleteRecipe(recipe);
        showBaseCommands();
    });

    public final Command Exit = new Command("Exit", () -> {
        recipeApp.finish();
    });

    private void showBaseCommands() {
        Command command = BASE_COMMANDS.get(io.chooseOperation(BASE_COMMANDS, "Choose operation"));
        lastExecutedCommand = command;
        command.execute();
    }

    public final List<Command> INITIAL_COMMANDS = Arrays.asList(LOGIN, REGISTER);
    public final List<Command> BASE_COMMANDS = Arrays.asList(GET_RECIPES, DELETE_RECIPE, ADD_RECIPE, Exit);

    public void chooseOperation(boolean isInitial) {
        if (isInitial) {
            Command command = INITIAL_COMMANDS.get(io.chooseOperation(INITIAL_COMMANDS, "Welcome!!!"));
            lastExecutedCommand = command;
            command.execute();
        } else {
            showBaseCommands();
        }
    }
}
