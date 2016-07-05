package org.jcloarca.myrecipes.recipelist.ui.adapters;

import org.jcloarca.myrecipes.entities.Recipe;

/**
 * Created by JCLoarca on 7/4/2016 9:28 PM.
 */
public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
