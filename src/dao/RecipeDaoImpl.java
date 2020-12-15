package dao;

import model.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDaoImpl implements RecipeDao {

    private final Connection connection;

    public RecipeDaoImpl(Connection connection) throws SQLException {
        this.connection = connection;
        Driver driver = new org.h2.Driver();
        DriverManager.registerDriver(driver);
    }

    public RecipeDaoImpl() throws SQLException {
        this(DriverManager.getConnection("jdbc:h2:~/test", "sa", ""));
    }

    @Override
    public List<Recipe> getRecipes(String mail) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM recipe WHERE ownerMail = '" + mail + "'");
        while (resultSet.next()) {
            String dbOwnerMail = resultSet.getString(COLUMN_OWNER_MAIL);
            String dbTitle = resultSet.getString(COLUMN_TITLE);
            double dbMakingTime = resultSet.getDouble(COLUMN_MAKING_TIME);
            String dbIngredients = resultSet.getString(COLUMN_INGREDIENTS);
            Recipe recipe = new Recipe(dbOwnerMail, dbTitle, dbMakingTime);
            recipe.setIngredients(dbIngredients);
            recipes.add(recipe);
        }
        return recipes;
    }

    @Override
    public void addRecipe(Recipe recipe) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO recipe VALUES (?,?,?,?)");
        preparedStatement.setString(1, recipe.getOwnerMail());
        preparedStatement.setString(2, recipe.getTitle());
        preparedStatement.setDouble(3, recipe.getMakingTime());
        preparedStatement.setString(4, recipe.joinIngredientsToString());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void deleteRecipeByName(String title) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM recipe WHERE title = '" + title + "'");
        statement.close();
    }
}
