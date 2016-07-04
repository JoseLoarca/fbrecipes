package org.jcloarca.myrecipes.recipemain;

import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.recipemain.events.RecipeMainEvent;
import org.jcloarca.myrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by JCLoarca on 7/4/2016 12:15 AM.
 */
public interface RecipeMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    void imageReady();
    void imageError(String error);

    RecipeMainView getView();
}
