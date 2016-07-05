package org.jcloarca.myrecipes.recipelist;

import org.jcloarca.myrecipes.entities.Recipe;

/**
 * Created by JCLoarca on 7/4/2016 9:26 PM.
 */
public interface StoredRecipesInteractor {
    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);
}
