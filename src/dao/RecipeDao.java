package dao;

import model.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface RecipeDao {

    String COLUMN_OWNER_MAIL = "ownerMail";
    String COLUMN_TITLE = "title";
    String COLUMN_MAKING_TIME = "makingTime";
    String COLUMN_INGREDIENTS = "ingredients";

    List<Recipe> getRecipes(String mail) throws SQLException;

    void addRecipe(Recipe recipe) throws SQLException;

    void deleteRecipeByName(String name) throws SQLException;

}
