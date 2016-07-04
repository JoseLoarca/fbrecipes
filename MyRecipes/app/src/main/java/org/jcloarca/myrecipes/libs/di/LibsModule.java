package org.jcloarca.myrecipes.libs.di;

import android.app.Activity;

import org.jcloarca.myrecipes.libs.GlideImageLoader;
import org.jcloarca.myrecipes.libs.GreenRobotEventBus;
import org.jcloarca.myrecipes.libs.base.EventBus;
import org.jcloarca.myrecipes.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JCLoarca on 7/3/2016 10:24 PM.
 */
@Module
public class LibsModule {
    Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }
}
