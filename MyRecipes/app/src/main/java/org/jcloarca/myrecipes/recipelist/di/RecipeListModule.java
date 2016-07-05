package org.jcloarca.myrecipes.recipelist.di;

import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.libs.base.EventBus;
import org.jcloarca.myrecipes.libs.base.ImageLoader;
import org.jcloarca.myrecipes.recipelist.RecipeListInteractor;
import org.jcloarca.myrecipes.recipelist.RecipeListInteractorImpl;
import org.jcloarca.myrecipes.recipelist.RecipeListPresenter;
import org.jcloarca.myrecipes.recipelist.RecipeListPresenterImpl;
import org.jcloarca.myrecipes.recipelist.RecipeListRepository;
import org.jcloarca.myrecipes.recipelist.RecipeListRepositoryImpl;
import org.jcloarca.myrecipes.recipelist.StoredRecipesInteractor;
import org.jcloarca.myrecipes.recipelist.StoredRecipesInteractorImpl;
import org.jcloarca.myrecipes.recipelist.ui.RecipeListView;
import org.jcloarca.myrecipes.recipelist.ui.adapters.OnItemClickListener;
import org.jcloarca.myrecipes.recipelist.ui.adapters.RecipesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JCLoarca on 7/4/2016 11:30 PM.
 */
@Module
public class RecipeListModule {
    RecipeListView view;
    OnItemClickListener onItemClickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    public RecipeListModule(RecipeListView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeListView provideRecipeListView() {
        return this.view;
    }

    @Provides @Singleton
    RecipeListPresenter provideRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor) {
        return new RecipeListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
    }

    @Provides @Singleton
    RecipeListInteractor provideRecipeListInteractor(RecipeListRepository repository) {
        return new RecipeListInteractorImpl(repository);
    }

    @Provides @Singleton
    StoredRecipesInteractor provideStoredRecipesInteractor(RecipeListRepository repository) {
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeListRepository provideRecipeListRepository(EventBus eventBus) {
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    RecipesAdapter provideRecipesAdapter(List<Recipe> recipes, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new RecipesAdapter(recipes, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides @Singleton
    List<Recipe> provideRecipesList() {
        return new ArrayList<Recipe>();
    }
}
