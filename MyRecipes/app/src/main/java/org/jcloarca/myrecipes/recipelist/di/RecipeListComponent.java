package org.jcloarca.myrecipes.recipelist.di;

import org.jcloarca.myrecipes.libs.di.LibsModule;
import org.jcloarca.myrecipes.recipelist.RecipeListPresenter;
import org.jcloarca.myrecipes.recipelist.ui.adapters.RecipesAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JCLoarca on 7/4/2016 11:30 PM.
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    //void inject(RecipeListActivity activity);
    RecipeListPresenter getPresenter();
    RecipesAdapter getAdapter();
}