package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe {

    private final String ownerMail;
    private final String title;
    private final double makingTime;
    private List<String> ingredients;

    public Recipe(String ownerMail, String title, double makingTime) {
        this.ownerMail = ownerMail;
        this.title = title;
        this.makingTime = makingTime;
        ingredients = new ArrayList<>();
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public String getTitle() {
        return title;
    }

    public double getMakingTime() {
        return makingTime;
    }

    public void setIngredients(String ingredientsString) {
        ingredients = Arrays.asList(ingredientsString.split(",").clone());
    }

    public String joinIngredientsToString() {
        return String.join(",", ingredients);
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", makingTime=" + makingTime + "\ningredients=" + joinIngredientsToString();
    }
}
