package org.jcloarca.myrecipes.recipemain.di;

import org.jcloarca.myrecipes.api.RecipeClient;
import org.jcloarca.myrecipes.api.RecipeService;
import org.jcloarca.myrecipes.libs.base.EventBus;
import org.jcloarca.myrecipes.recipemain.GetNextRecipeInteractor;
import org.jcloarca.myrecipes.recipemain.GetNextRecipeInteractorImpl;
import org.jcloarca.myrecipes.recipemain.RecipeMainPresenter;
import org.jcloarca.myrecipes.recipemain.RecipeMainPresenterImpl;
import org.jcloarca.myrecipes.recipemain.RecipeMainRepository;
import org.jcloarca.myrecipes.recipemain.RecipeMainRepositoryImpl;
import org.jcloarca.myrecipes.recipemain.SaveRecipeInteractor;
import org.jcloarca.myrecipes.recipemain.SaveRecipeInteractorImpl;
import org.jcloarca.myrecipes.recipemain.ui.RecipeMainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JCLoarca on 7/4/2016 7:18 PM.
 */
@Module
public class RecipeMainModule {
    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeMainView provideRecipeMainView() {
        return this.view;
    }

    @Provides @Singleton
    RecipeMainPresenter provideRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor save, GetNextRecipeInteractor getNext) {
        return new RecipeMainPresenterImpl(eventBus, view, save, getNext);
    }

    @Provides @Singleton
    SaveRecipeInteractor provideSaveRecipeInteractor(RecipeMainRepository repository) {
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    GetNextRecipeInteractor provideGetNextRecipeInteractor(RecipeMainRepository repository) {
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeMainRepository provideRecipeMainRepository(EventBus eventBus, RecipeService service) {
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    RecipeService provideRecipeService() {
        RecipeClient client = new RecipeClient();
        return client.getRecipeService();
    }
}
