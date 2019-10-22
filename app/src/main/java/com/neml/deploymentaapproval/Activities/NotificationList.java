package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.neml.deploymentaapproval.NetworkUtils.HttpsTrustManager;
import com.neml.deploymentaapproval.NetworkUtils.SingleRequestQueue;
import com.neml.deploymentaapproval.R;
import com.neml.deploymentaapproval.Utils.Constants;
import com.neml.deploymentaapproval.Utils.utils;

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
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        appPreference = new AppPreference(this);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        notifyItemList = new ArrayList<>();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        HttpsTrustManager.allowAllSSL();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NOTIFICATION LIST");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        initUI();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(utils.isNetworkAvailable(NotificationList.this)){
                    if(notifyItemList.isEmpty()){
                        postConnectionNotificationList(NotificationList.this, Constants.UrlLinks.details,appPreference.getMantisID(),"");
                        swipeRefreshLayout.setRefreshing(false);
                    }else{
                        notifyItemList.clear();
                        postConnectionNotificationList(NotificationList.this, Constants.UrlLinks.details,appPreference.getMantisID(),"");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }else{
                    utils.getSimpleDialog(NotificationList.this,"Deployment Approval","Internet not Available").show();
                }
            }
        });
       // postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
    }

    private void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public  void postConnectionNotificationList(final Context mContext, String url, final String mantisId, final String deploymentNo) {
        showpDialog();
        Map<String, String> params = new HashMap();
        params.put(Constants.postAttributeName.deploymentNo, "");
        params.put(Constants.postAttributeName.mantisID, mantisId);
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
                        String requesterName = jsonObj.getString("preparedName");
                        String requesterManager = jsonObj.getString("approvedByName");
                        String applicationUrl = jsonObj.getString("projectUrl");
                        String createdDate = jsonObj.getString("createdDateStr");
                        String uatApprovalName = jsonObj.getString("uatApprovarName");
                        String projectName = jsonObj.getString("projectName");
                        String isApproverApproved = jsonObj.getString("isApproverApproved");
                        String DeploymentType = jsonObj.getString("deploymentType");
                        String srnNo = jsonObj.getString("srnNo");
                        String versionno = jsonObj.getString("versionNo");
                        modelNotificationList =  new ModelNotificationList();

                        modelNotificationList.setDeploymentNo(DeploymentNo);
                        modelNotificationList.setRfcNo(rfcSeqNo);
                        modelNotificationList.setPreparedBy(requesterName);
                        modelNotificationList.setApprovedBy(requesterManager);
                        modelNotificationList.setProjectUrl("ABCD");
                        modelNotificationList.setCreatedDate(createdDate);
                        modelNotificationList.setUatApprovar(uatApprovalName);
                        modelNotificationList.setProjectName(projectName);
                        modelNotificationList.setDeploymentType(DeploymentType);
                        modelNotificationList.setSrnNo(srnNo);
                        modelNotificationList.setIsArroverApproved(isApproverApproved);
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
                //Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_items_page, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Intent intentAbout = new Intent(NotificationList.this,AboutActivity.class);
                startActivity(intentAbout);
                break;

            case R.id.menuSettings:
                if(utils.isNetworkAvailable(NotificationList.this)){
                    postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getMantisID(),"");
                }else{
                    utils.getSimpleDialog(NotificationList.this,getResources().getString(R.string.app_name),"Internet not Available").show();
                }

                break;

            case R.id.menuLogout:
                appPreference.setUserID(null);
                appPreference.setPassword(null);
                appPreference.setMantisID(null);
                appPreference.setloginDone(false);
                Intent intentLogin = new Intent(NotificationList.this,MainActivity.class);
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogin);
                break;

            case  R.id.menuNotification:
                Intent intent = new Intent(NotificationList.this,NotificationList.class);
                startActivity(intent);

        }
        return true;
    }

    @Override
    protected void onStart() {
        if(utils.isNetworkAvailable(NotificationList.this)){
            if(notifyItemList.isEmpty()){
                postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getMantisID(),"");
            }else{
                notifyItemList.clear();
                postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getMantisID(),"");
            }
        }else{
            utils.getSimpleDialog(NotificationList.this,"Deployment Approval","Internet not Available").show();
        }

        super.onStart();
    }

}
