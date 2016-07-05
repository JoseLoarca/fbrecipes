package org.jcloarca.myrecipes.recipemain.di;

import org.jcloarca.myrecipes.libs.base.ImageLoader;
import org.jcloarca.myrecipes.libs.di.LibsModule;
import org.jcloarca.myrecipes.recipemain.RecipeMainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by JCLoarca on 7/4/2016 7:17 PM.
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
