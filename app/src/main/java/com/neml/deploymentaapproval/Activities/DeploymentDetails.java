package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.neml.deploymentaapproval.Database.AppPreference;
import com.neml.deploymentaapproval.Listerner.AlertDialogListerner;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.Model.ModelNotificationList;
import com.neml.deploymentaapproval.NetworkUtils.HttpsTrustManager;
import com.neml.deploymentaapproval.NetworkUtils.NetworkUtils;
import com.neml.deploymentaapproval.NetworkUtils.SingleRequestQueue;
import com.neml.deploymentaapproval.R;
import com.neml.deploymentaapproval.Utils.Constants;
import com.neml.deploymentaapproval.Utils.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeploymentDetails extends AppCompatActivity {
    private TextView deploymentNo, rfcSeqno, requesterName, requestermanager, feedback,
    createdDate, uatApprovalName, projectName, deploymentType, srnNo, versionNo;

    private Button approve, reject;
    Bundle bundle;
    AppPreference appPreference;
    ModelNotificationList modelNotificationList;
    // Progress dialog
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deployment_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("DEPLOYMENT DETAILS");
        toolbar.setTitleTextColor(Color.WHITE);
        ResourcesCompat.getColor(getResources(), R.color.white, null);
        setSupportActionBar(toolbar);
        HttpsTrustManager.allowAllSSL();

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        appPreference = new AppPreference(DeploymentDetails.this);
        initUI();
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        modelNotificationList = (ModelNotificationList) getIntent().getSerializableExtra("serialzable");
        setFields();

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utils.isNetworkAvailable(DeploymentDetails.this)){
                    postApprove(DeploymentDetails.this, Constants.UrlLinks.approve,modelNotificationList.getDeploymentNo(),appPreference.getUserID());
                }else{
                    utils.getSimpleDialog(DeploymentDetails.this,getResources().getString(R.string.app_name),"Internet not Available").show();
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_details, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Intent intentAbout = new Intent(DeploymentDetails.this,AboutActivity.class);
                startActivity(intentAbout);
                break;


            case R.id.menuLogout:
                appPreference.setUserID(null);
                appPreference.setPassword(null);
                appPreference.setloginDone(false);
                Intent intentLogin = new Intent(DeploymentDetails.this,MainActivity.class);
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogin);
                break;


        }
        return true;
    }



    private void setFields() {
        deploymentNo.setText(modelNotificationList.getDeploymentNo());
        requesterName.setText(modelNotificationList.getPreparedBy());
        requestermanager.setText(modelNotificationList.getApprovedBy());
        createdDate.setText(modelNotificationList.getCreatedDate().substring(0,11));
        uatApprovalName.setText(modelNotificationList.getUatApprovar());
        projectName.setText(modelNotificationList.getProjectName());
        deploymentType.setText(modelNotificationList.getDeploymentType());
        srnNo.setText(modelNotificationList.getSrnNo());
        versionNo.setText(modelNotificationList.getVersionNo());
        if(modelNotificationList.getIsArroverApproved().equalsIgnoreCase("Y")){
            approve.setVisibility(View.GONE);
            feedback.setVisibility(View.VISIBLE);
            feedback.setText("Deployment Already Approved");
        }else{
            approve.setVisibility(View.VISIBLE);
        }

    }

    private void initUI() {
        deploymentNo = findViewById(R.id.deploymentNo);
        requesterName = findViewById(R.id.requesterName);
        requestermanager = findViewById(R.id.requesterManager);
        createdDate = findViewById(R.id.createdDate);
        uatApprovalName = findViewById(R.id.uatApproval);
        projectName = findViewById(R.id.projectName);
        deploymentType = findViewById(R.id.deploymentType);
        srnNo = findViewById(R.id.srnNo);
        versionNo = findViewById(R.id.versionNo);
        feedback = findViewById(R.id.feedbak);

        approve = findViewById(R.id.approve);
        reject = findViewById(R.id.reject);
    }
    public  void postApprove(final Context mContext, String url, String deploymentNo,String userId){
        try {
            showpDialog();
            Map<String, String> params = new HashMap();
            params.put(Constants.postAttributeName.deploymentNo, deploymentNo);
            params.put(Constants.postAttributeName.userId, userId);

            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //TODO: handle success
                    try {
                        hidepDialog();
                        Logg.d(response.toString());
                        if(response!=null){
                            String status = response.getString("status");
                            if(status.equalsIgnoreCase("Deployment Approved Successfully")){
                                utils.getSimpleDialog(mContext, response.getString("status"), new AlertDialogListerner() {
                                    @Override
                                    public void onConfirmed(boolean f_isActionConfirmed) {
                                        finish();
                                    }
                                }).show();

                            }else{
                                utils.getSimpleDialog(mContext,response.getString("status")).show();
                            }
                        }else{
                            utils.getSimpleDialog(mContext,"Connection Error").show();
                        }


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
                    utils.getSimpleDialog(mContext,error.getMessage().toString()).show();
                    //TODO: handle failure
                }
            });
            SingleRequestQueue.getInstance(mContext).addToRequestQueue(jsonRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
