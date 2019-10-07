package com.neml.deploymentaapproval.NetworkUtils;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    Context mContext;
    NetworkUtils(Context context) throws JSONException {
        this.mContext = context;
    }


    public static void postConnectionSendToken(final Context mContext, String url, String token,String userId){
        try {
            Map<String, String> params = new HashMap();
            params.put(Constants.postAttributeName.fcmToken, token);
            params.put(Constants.postAttributeName.userId,userId);
            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //TODO: handle success
                    try {
                        if(response!=null){
                            Logg.d(response.toString());
                            Toast.makeText(mContext, response.getString("status"), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mContext, "Null Response", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                    //TODO: handle failure
                }
            });
            SingleRequestQueue.getInstance(mContext).addToRequestQueue(jsonRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
