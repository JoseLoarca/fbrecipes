package org.jcloarca.myrecipes.recipelist;

import org.jcloarca.myrecipes.entities.Recipe;

/**
 * Created by JCLoarca on 7/4/2016 9:27 PM.
 */
public interface RecipeListRepository {
    void getSavedRecipes();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
}
