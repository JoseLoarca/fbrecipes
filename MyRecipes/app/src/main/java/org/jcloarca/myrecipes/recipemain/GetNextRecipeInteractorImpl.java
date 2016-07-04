package org.jcloarca.myrecipes.recipemain;

import java.util.Random;

/**
 * Created by JCLoarca on 7/4/2016 1:04 AM.
 */
public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor{
    RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();
    }
}
