package recipes_app;

import auth.AuthChoice;
import com.sun.istack.internal.Nullable;
import model.Recipe;
import model.User;

import java.util.List;

public interface RecipeAppTemplate {

    void start();

    void authUser(AuthChoice authChoice);

    void registerUser(User user);

    void finish();

    @Nullable
    List<Recipe> getRecipes();

    void addRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);
}
