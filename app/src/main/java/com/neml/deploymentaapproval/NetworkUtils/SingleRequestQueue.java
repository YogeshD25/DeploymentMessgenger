package com.neml.deploymentaapproval.NetworkUtils;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleRequestQueue {
    private static SingleRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private SingleRequestQueue(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue(); //Alternative
    }

    public static synchronized SingleRequestQueue getInstance(Context context){
        if(mInstance == null){
            mInstance = new SingleRequestQueue(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
