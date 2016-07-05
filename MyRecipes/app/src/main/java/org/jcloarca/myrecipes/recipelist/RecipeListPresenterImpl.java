package org.jcloarca.myrecipes.recipelist;

import org.greenrobot.eventbus.Subscribe;
import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.libs.base.EventBus;
import org.jcloarca.myrecipes.recipelist.events.RecipeListEvent;
import org.jcloarca.myrecipes.recipelist.ui.RecipeListView;

/**
 * Created by JCLoarca on 7/4/2016 10:54 PM.
 */
public class RecipeListPresenterImpl implements RecipeListPresenter {
    private EventBus eventBus;
    private RecipeListView view;
    private RecipeListInteractor listInteractor;
    private StoredRecipesInteractor storedInteractor;

    public RecipeListPresenterImpl(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.listInteractor = listInteractor;
        this.storedInteractor = storedInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getRecipes() {
        listInteractor.execute();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        storedInteractor.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav = recipe.getFavorite();
        recipe.setFavorite(!fav);
        storedInteractor.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if (this.view != null) {
            switch (event.getType()){
                case RecipeListEvent.READ_EVENT:
                    view.setRecipes(event.getRecipeList());
                    break;
                case RecipeListEvent.UPDATE_EVENT:
                    view.recipeUpdated();
                    break;
                case RecipeListEvent.DELETE_EVENT:
                    Recipe recipe = event.getRecipeList().get(0);
                    view.recipeDeleted(recipe);
                    break;

            }
        }

    }

    @Override
    public RecipeListView getView() {
        return this.view;
    }
}
