package com.antnzr.alef.utils;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.antnzr.alef.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class ImageDownloader {
    private static final String TAG = ImageDownloader.class.getSimpleName();

    private static RequestListener<Drawable> requestListener = new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            if (e != null) {
                Log.d(TAG, "onLoadFailed: exception: " + e.getMessage() + " \n" + "object: " + model);
            }

            // important to return false so the error placeholder can be placed
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            // everything worked out, so probably nothing to do
            return false;
        }
    };

    public static void setImage(ImageView view, String url) {
        GlideApp.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.ic_image_download_background)
                .error(R.drawable.ic_error_image)
                .fallback(R.drawable.ic_error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(requestListener)
                .into(view);
    }
}
