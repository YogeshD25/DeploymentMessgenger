package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.neml.deploymentaapproval.Adapters.DeploymentAdapter;
import com.neml.deploymentaapproval.Adapters.NotifyListAdapter;
import com.neml.deploymentaapproval.Database.AppPreference;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.Model.ModelDisplayDetails;
import com.neml.deploymentaapproval.Model.ModelNotificationList;
import com.neml.deploymentaapproval.Model.ModelNotificationListItem;
import com.neml.deploymentaapproval.NetworkUtils.SingleRequestQueue;
import com.neml.deploymentaapproval.R;
import com.neml.deploymentaapproval.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ModelNotificationList> notifyItemList;
    // Progress dialog
    private ProgressDialog pDialog;
    ModelNotificationList modelNotificationList;
    AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        appPreference = new AppPreference(this);
        modelNotificationList =  new ModelNotificationList();
        notifyItemList = new ArrayList<>();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        initUI();
       // postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
    }

    private void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notifyItemList.add(new ModelNotificationList("12321312"));
        notifyItemList.add(new ModelNotificationList("12321312"));
        notifyItemList.add(new ModelNotificationList("12321312"));
        notifyItemList.add(new ModelNotificationList("12321312"));
        notifyItemList.add(new ModelNotificationList("12321312"));
        notifyItemList.add(new ModelNotificationList("12321312"));
        notifyItemList.add(new ModelNotificationList("12321312"));


        NotifyListAdapter adapter = new NotifyListAdapter(NotificationList.this, notifyItemList);
        recyclerView.setAdapter(adapter);
    }

    public  void postConnectionNotificationList(final Context mContext, String url, final String userId, final String deploymentNo) {
        showpDialog();
        Map<String, String> params = new HashMap();
        params.put(Constants.postAttributeName.deploymentNo, "");
        params.put(Constants.postAttributeName.userId, "NA");
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    hidepDialog();
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

                        notifyItemList.add(modelNotificationList);

                    }
                    NotifyListAdapter adapter = new NotifyListAdapter(NotificationList.this, notifyItemList);
                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                error.printStackTrace();
                Logg.d(error.toString());
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                //TODO: handle failure
            }
        });
        SingleRequestQueue.getInstance(mContext).addToRequestQueue(jsonRequest);

    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
