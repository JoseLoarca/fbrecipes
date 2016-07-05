package org.jcloarca.myrecipes.recipelist.ui;

import org.jcloarca.myrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by JCLoarca on 7/4/2016 9:11 PM.
 */
public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDeleted(Recipe recipe);
}
