package com.neml.deploymentaapproval.NetworkUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.neml.deploymentaapproval.Activities.DeploymentDetails;
import com.neml.deploymentaapproval.Activities.NotificationList;
import com.neml.deploymentaapproval.Adapters.NotifyListAdapter;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.Model.ModelNotificationList;
import com.neml.deploymentaapproval.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
    Context mContext;
    public static  ModelNotificationList modelNotificationList;
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

    public static  void postConnectionSpecific(final Context mContext, String url, String deploymentNo, String userId){

        Map<String, String> params = new HashMap();
        params.put(Constants.postAttributeName.deploymentNo, "");
        params.put(Constants.postAttributeName.userId, "NA");
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    modelNotificationList = new ModelNotificationList();
                    String data = response.getString("data");
                    JSONArray jsonArr = new JSONArray(data);
                    for (int i = 0; i < jsonArr.length(); i++)
                    {
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        System.out.println(jsonObj);
                        String DeploymentNo = jsonObj.getString("deploymentNo");
                        String rfcSeqNo = jsonObj.getString("rfcSeqNo");
                        String requesterName = jsonObj.getString("preparedBy");
                        String requesterManager = jsonObj.getString("approvedBy");
                        String applicationUrl = jsonObj.getString("projectUrl");
                        String createdDate = jsonObj.getString("createdDate");
                        String uatApprovalName = jsonObj.getString("uatApprovar");
                        String projectName = jsonObj.getString("projectName");
                        String DeploymentType = jsonObj.getString("deploymentType");
                        String srnNo = jsonObj.getString("srnNo");
                        String versionno = jsonObj.getString("versionNo");

                        modelNotificationList.setDeploymentNo(DeploymentNo);
                        modelNotificationList.setRfcNo(rfcSeqNo);
                        modelNotificationList.setPreparedBy(requesterName);
                        modelNotificationList.setApprovedBy(requesterManager);
                        modelNotificationList.setProjectUrl("ABCD");
                        modelNotificationList.setCreatedDate("ABCD");
                        modelNotificationList.setUatApprovar(uatApprovalName);
                        modelNotificationList.setProjectName("ABCD");
                        modelNotificationList.setDeploymentType(DeploymentType);
                        modelNotificationList.setSrnNo(srnNo);
                        modelNotificationList.setVersionNo(versionno);
                    }
                    Intent send = new Intent(mContext, DeploymentDetails.class);
                    Bundle b = new Bundle();
                    b.putSerializable("serialzable",modelNotificationList);
                    send.putExtras(b);
                    mContext.startActivity(send);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Logg.d(error.toString());
                //Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                //TODO: handle failure
            }
        });
        SingleRequestQueue.getInstance(mContext).addToRequestQueue(jsonRequest);

    }

}
