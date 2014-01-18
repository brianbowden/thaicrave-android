package com.thaicrave.android.app;

import android.content.Context;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import com.thaicrave.android.toolbox.BitmapLruCache;
import com.thaicrave.android.toolbox.Utils;

import org.json.JSONObject;

public class Volleyball {

    private static final int MAX_IMAGE_CACHE_ENTRIES = 100;

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    private Volleyball() {}

    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(MAX_IMAGE_CACHE_ENTRIES));
    }

    public static String getUrl(String relativePath) {
        return TcEnv.get().ROOT_API_URL + relativePath;
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

    public static String getErrorMessage(VolleyError error) {
        try {
            if (error != null && error.networkResponse != null && error.networkResponse.data != null) {
                JSONObject resObj = new JSONObject(new String(error.networkResponse.data, "utf-8"));
                return resObj.getString("message");
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
