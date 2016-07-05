package org.jcloarca.myrecipes;

import android.app.Application;
import android.content.Intent;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.jcloarca.myrecipes.libs.di.LibsModule;
import org.jcloarca.myrecipes.login.ui.LoginActivity;
import org.jcloarca.myrecipes.recipelist.di.DaggerRecipeListComponent;
import org.jcloarca.myrecipes.recipelist.di.RecipeListComponent;
import org.jcloarca.myrecipes.recipelist.di.RecipeListModule;
import org.jcloarca.myrecipes.recipelist.ui.RecipeListActivity;
import org.jcloarca.myrecipes.recipelist.ui.RecipeListView;
import org.jcloarca.myrecipes.recipelist.ui.adapters.OnItemClickListener;
import org.jcloarca.myrecipes.recipemain.di.DaggerRecipeMainComponent;
import org.jcloarca.myrecipes.recipemain.di.RecipeMainComponent;
import org.jcloarca.myrecipes.recipemain.di.RecipeMainModule;
import org.jcloarca.myrecipes.recipemain.ui.RecipeMainActivity;
import org.jcloarca.myrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by JCLoarca on 7/3/2016 9:21 PM.
 */
public class MyRecipesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view) {
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }

    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener onItemClickListener) {
        return DaggerRecipeListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view, onItemClickListener))
                .build();
    }
}
