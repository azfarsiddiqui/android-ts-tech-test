package com.tigerspike.network;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyManager {

    private static VolleyManager sInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private LruBitmapCache mCache;

    private VolleyManager(Context context, int cacheSize) {

        mRequestQueue = Volley.newRequestQueue(context);
        mCache = new LruBitmapCache(cacheSize);
        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {

            public void putBitmap(String url, Bitmap bitmap) {

                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {

                return mCache.get(url);
            }
        });
    }

    public static VolleyManager getInstance() {

        if (sInstance == null) {
            throw new IllegalStateException("Did you call VolleyManager.initialize()?");
        }

        return sInstance;
    }

    public static void initialize(Context context, int cacheSize) {

        if (sInstance == null) {
            sInstance = new VolleyManager(context, cacheSize);
        }
    }

    public RequestQueue getRequestQueue() {

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {

        return mImageLoader;
    }
}
