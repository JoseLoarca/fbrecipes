package org.jcloarca.myrecipes.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by JCLoarca on 7/3/2016 9:51 PM.
 */
@Database(name = RecipesDatabase.NAME, version = RecipesDatabase.VERSION)
public class RecipesDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "Recipes";
}
