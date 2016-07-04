package org.jcloarca.myrecipes.recipemain;

import org.jcloarca.myrecipes.BuildConfig;
import org.jcloarca.myrecipes.api.RecipeSearchResponse;
import org.jcloarca.myrecipes.api.RecipeService;
import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.libs.base.EventBus;
import org.jcloarca.myrecipes.recipemain.events.RecipeMainEvent;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JCLoarca on 7/4/2016 1:08 AM.
 */
public class RecipeMainRepositoryImpl implements RecipeMainRepository{
    private int recipePage;
    private EventBus eventBus;
    private RecipeService service;

    public RecipeMainRepositoryImpl(EventBus eventBus, RecipeService service) {
        this.eventBus = eventBus;
        this.service = service;
        this.recipePage = new Random().nextInt(RECIPE_RANGE);
    }

    @Override
    public void setRecipePage(int recipePage) {
        this.recipePage = recipePage;
    }

    @Override
    public void getNextRecipe() {
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, RECENT_SORT, COUNT, recipePage);
        call.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if (response.isSuccess()) {
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if (recipeSearchResponse.getCount() == 0){
                        setRecipePage(new Random().nextInt(RECIPE_RANGE));
                        getNextRecipe();
                    } else {
                        Recipe recipe = recipeSearchResponse.getFirstRecipe();
                        if (recipe != null) {
                            post(recipe);
                        } else {
                            post(response.message());
                        }

                    }
                } else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                post(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
        post();
    }

    private void post(String error, int type, Recipe recipe){
        RecipeMainEvent event = new RecipeMainEvent();
        event.setType(type);
        event.setError(error);
        event.setRecipe(recipe);
        eventBus.post(event);
    }

    private void post(Recipe recipe){
        post(null, RecipeMainEvent. NEXT_EVENT, recipe);
    }

    private void post(String error){
        post(error, RecipeMainEvent.NEXT_EVENT, null);
    }

    private void post(){
        post(null, RecipeMainEvent.SAVE_EVENT, null);
    }
}