package org.jcloarca.myrecipes.recipemain.ui;

import org.jcloarca.myrecipes.entities.Recipe;

/**
 * Created by JCLoarca on 7/4/2016 12:13 AM.
 */
public interface RecipeMainView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();
    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);
}
