package org.jcloarca.myrecipes.recipelist.events;

import org.jcloarca.myrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by JCLoarca on 7/4/2016 9:13 PM.
 */
public class RecipeListEvent {
    private int type;
    private List<Recipe> recipeList;

    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;
    public final static int DELETE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
