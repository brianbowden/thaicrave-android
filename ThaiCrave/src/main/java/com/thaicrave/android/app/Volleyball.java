package com.thaicrave.android.app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.thaicrave.android.toolbox.BitmapLruCache;

public class Volleyball {

    private static final int MAX_IMAGE_CACHE_ENTRIES = 100;

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    private Volleyball() {}

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(MAX_IMAGE_CACHE_ENTRIES));
    }

    public static <Model> void addReq(Request<Model> request) {
        getQueue().add(request);
    }

    public static RequestQueue getQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        }
        else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        }
        else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
