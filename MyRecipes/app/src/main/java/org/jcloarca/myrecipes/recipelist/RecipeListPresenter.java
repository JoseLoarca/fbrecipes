package org.jcloarca.myrecipes.recipelist;

import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.recipelist.events.RecipeListEvent;
import org.jcloarca.myrecipes.recipelist.ui.RecipeListView;

/**
 * Created by JCLoarca on 7/4/2016 9:12 PM.
 */
public interface RecipeListPresenter {
    void onCreate();
    void onDestroy();

    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    RecipeListView getView();
}
