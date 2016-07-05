package org.jcloarca.myrecipes.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;

import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.libs.base.EventBus;
import org.jcloarca.myrecipes.recipelist.events.RecipeListEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JCLoarca on 7/4/2016 11:09 PM.
 */
public class RecipeListRepositoryImpl implements RecipeListRepository {
    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(false, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipes) {
        RecipeListEvent event = new RecipeListEvent();
        event.setRecipeList(recipes);
        event.setType(type);
        eventBus.post(event);
    }

    private void post() {
        post(RecipeListEvent.UPDATE_EVENT, null);
    }
}
