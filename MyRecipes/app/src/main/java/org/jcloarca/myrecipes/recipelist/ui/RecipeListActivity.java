package org.jcloarca.myrecipes.recipelist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;

import org.jcloarca.myrecipes.MyRecipesApp;
import org.jcloarca.myrecipes.R;
import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.login.ui.LoginActivity;
import org.jcloarca.myrecipes.recipelist.RecipeListPresenter;
import org.jcloarca.myrecipes.recipelist.di.RecipeListComponent;
import org.jcloarca.myrecipes.recipelist.ui.adapters.OnItemClickListener;
import org.jcloarca.myrecipes.recipelist.ui.adapters.RecipesAdapter;
import org.jcloarca.myrecipes.recipemain.ui.RecipeMainActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    RecipesAdapter adapter;
    RecipeListPresenter presenter;
    private RecipeListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();

        presenter.onCreate();
        presenter.getRecipes();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_main) {
            navigateToMainScreen();
            return true;
        } else if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }

    private void setupInjection() {
        MyRecipesApp app = (MyRecipesApp)getApplication();
        component = app.getRecipeListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    public RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

    public RecipesAdapter getAdapter() {
        return component.getAdapter();
    }
}
