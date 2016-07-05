package org.jcloarca.myrecipes.recipelist;

/**
 * Created by JCLoarca on 7/4/2016 11:07 PM.
 */
public class RecipeListInteractorImpl implements RecipeListInteractor {
    private RecipeListRepository repository;

    public RecipeListInteractorImpl(RecipeListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedRecipes();
    }
}
