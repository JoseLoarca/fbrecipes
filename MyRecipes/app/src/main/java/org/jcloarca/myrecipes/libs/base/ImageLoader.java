package org.jcloarca.myrecipes.libs.base;

import android.widget.ImageView;

/**
 * Created by JCLoarca on 7/3/2016 10:18 PM.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
