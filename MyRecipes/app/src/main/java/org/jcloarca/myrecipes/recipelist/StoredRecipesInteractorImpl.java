package org.jcloarca.myrecipes.recipelist;

import org.jcloarca.myrecipes.entities.Recipe;

/**
 * Created by JCLoarca on 7/4/2016 11:08 PM.
 */
public class StoredRecipesInteractorImpl implements StoredRecipesInteractor {
    private RecipeListRepository repository;

    public StoredRecipesInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Recipe recipe) {
        repository.updateRecipe(recipe);
    }

    @Override
    public void executeDelete(Recipe recipe) {
        repository.removeRecipe(recipe);
    }
}
