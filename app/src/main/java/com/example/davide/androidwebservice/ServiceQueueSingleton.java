package com.example.davide.androidwebservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by davide on 27/03/2018.
 */

public class ServiceQueueSingleton {
    private static ServiceQueueSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private ServiceQueueSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ServiceQueueSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ServiceQueueSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue =
                    Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}