package org.jcloarca.myrecipes.recipemain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.jcloarca.myrecipes.MyRecipesApp;
import org.jcloarca.myrecipes.R;
import org.jcloarca.myrecipes.recipelist.ui.RecipeListActivity;
import org.jcloarca.myrecipes.entities.Recipe;
import org.jcloarca.myrecipes.libs.base.ImageLoader;
import org.jcloarca.myrecipes.recipemain.RecipeMainPresenter;
import org.jcloarca.myrecipes.recipemain.di.RecipeMainComponent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeMainActivity extends AppCompatActivity implements RecipeMainView, SwipeGestureListener{

    @Bind(R.id.imgRecipe)
    ImageView imgRecipe;
    @Bind(R.id.imgDismiss)
    ImageButton imgDismiss;
    @Bind(R.id.imgKeep)
    ImageButton imgKeep;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutContainer)
    RelativeLayout layoutContainer;

    private Recipe currentRecipe;
    private ImageLoader imageLoader;
    private RecipeMainPresenter presenter;
    private RecipeMainComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main);
        ButterKnife.bind(this);

        setupInjection();
        setupImageLoader();
        setupGestureDetection();
        presenter.onCreate();
        presenter.getNextRecipe();
    }

    private void setupGestureDetection(){
        final GestureDetector gestureDetector = new GestureDetector(this, new SwipeGestureDetector(this));
        View.OnTouchListener gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        imgRecipe.setOnTouchListener(gestureListener);
    }

    private void setupImageLoader() {
        RequestListener glideRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                presenter.imageError(e.getLocalizedMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                presenter.imageReady();
                return false;
            }
        };
        imageLoader.setOnFinishedImageLoadingListener(glideRequestListener);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_list){
            navigateToListScreen();
        }else if(id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        MyRecipesApp app = (MyRecipesApp)getApplication();
        app.logout();
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, RecipeListActivity.class));
    }

    private void setupInjection() {
        MyRecipesApp app = (MyRecipesApp)getApplication();
        component = app.getRecipeMainComponent(this, this);
        imageLoader = getImageLoader();
        presenter = getPresenter();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showUIElements() {
        imgKeep.setVisibility(View.VISIBLE);
        imgDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUIElements() {
        imgKeep.setVisibility(View.GONE);
        imgDismiss.setVisibility(View.GONE);
    }

    private void clearImage(){
        imgRecipe.setImageResource(0);
    }

    @Override
    public void saveAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_save);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }
        });
        imgRecipe.startAnimation(anim);
    }

    @Override
    public void dismissAnimation() {
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_dismiss);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearImage();
            }
        });
        imgRecipe.startAnimation(anim);
    }

    @OnClick(R.id.imgKeep)
    @Override
    public void onKeep(){
        if(currentRecipe!=null){
            presenter.saveRecipe(currentRecipe);
        }
    }

    @OnClick(R.id.imgDismiss)
    @Override
    public void onDismiss(){
        presenter.dismissRecipe();
    }

    @Override
    public void onRecipeSaved() {
        Snackbar.make(layoutContainer, R.string.recipemain_notice_saved, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        imageLoader.load(imgRecipe, recipe.getImageURL());
    }

    @Override
    public void onGetRecipeError(String error) {
        String msgError = String.format(getString(R.string.recipemain_error), error);
        Snackbar.make(layoutContainer, msgError, Snackbar.LENGTH_SHORT).show();
    }

    public ImageLoader getImageLoader() {
        return component.getImageLoader();
    }

    public RecipeMainPresenter getPresenter() {
        return component.getPresenter();
    }
}
