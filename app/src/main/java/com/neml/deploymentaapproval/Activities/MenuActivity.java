package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;;
import com.neml.deploymentaapproval.Adapters.DeploymentAdapter;
import com.neml.deploymentaapproval.Database.AppPreference;
import com.neml.deploymentaapproval.Model.ModelDisplayDetails;
import com.neml.deploymentaapproval.Model.ModelNotificationList;
import com.neml.deploymentaapproval.Logger.*;
import com.neml.deploymentaapproval.NetworkUtils.SingleRequestQueue;
import com.neml.deploymentaapproval.R;
import com.neml.deploymentaapproval.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    GestureDetector gestureDetector;
    RecyclerView recyclerView;
    ArrayList<ModelDisplayDetails> deploymentList;
    ArrayList<ModelNotificationList> notificationListArrayList;
    ModelNotificationList modelNotificationList;
    TextView title;
    AppPreference appPreference;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        appPreference = new AppPreference(this);
        modelNotificationList = new ModelNotificationList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Deployment Approval");
        setSupportActionBar(toolbar);

        initUI();

        notificationListArrayList = new ArrayList<>();
        //postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"")

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
                Intent intentAbout = new Intent(MenuActivity.this,AboutActivity.class);
                startActivity(intentAbout);
                break;

            case R.id.menuSettings:
               // postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
                break;

            case R.id.menuLogout:
                appPreference.setUserID(null);
                appPreference.setPassword(null);
                appPreference.setloginDone(false);
                Intent intentLogin = new Intent(MenuActivity.this,MainActivity.class);
                intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogin);
                break;

            case  R.id.menuNotification:
                Intent intent = new Intent(MenuActivity.this,NotificationList.class);
                startActivity(intent);

        }
        return true;
    }

    private void initUI() {
        title =  findViewById(R.id.titleText);
        title.setText("WELCOME "+appPreference.getUserID());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notificationListArrayList = new ArrayList<>();
        notificationListArrayList.add(new ModelNotificationList("123217"));
        notificationListArrayList.add(new ModelNotificationList("908778"));
        notificationListArrayList.add(new ModelNotificationList("354321"));
        notificationListArrayList.add(new ModelNotificationList("768321"));
        DeploymentAdapter adapter = new DeploymentAdapter(MenuActivity.this, notificationListArrayList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
    public  void postConnectionNotificationList(final Context mContext, String url, final String userId,final String deploymentNo) {
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

                        notificationListArrayList.add(modelNotificationList);

                    }
                    DeploymentAdapter adapter = new DeploymentAdapter(MenuActivity.this, notificationListArrayList);
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
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent1, MotionEvent motionEvent2, float v, float v1) {
        if(motionEvent1.getY() - motionEvent2.getY() > 50){
            postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
            return true;
        }

        if(motionEvent2.getY() - motionEvent1.getY() > 50){
            postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
            return true;
        }

        if(motionEvent1.getX() - motionEvent2.getX() > 50){
//            Toast.makeText(MainActivity.this , " Swipe Left " , Toast.LENGTH_LONG).show();
            return true;
        }

        if(motionEvent2.getX() - motionEvent1.getX() > 50) {
//            Toast.makeText(MainActivity.this, " Swipe Right ", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return true ;
        }
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if(motionEvent1.getY() - motionEvent2.getY() > 50){
            postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
            return true;
        }

        if(motionEvent2.getY() - motionEvent1.getY() > 50){
            postConnectionNotificationList(this, Constants.UrlLinks.details,appPreference.getUserID(),"");
            return true;
        }

        if(motionEvent1.getX() - motionEvent2.getX() > 50){
            return true;
        }

        if(motionEvent2.getX() - motionEvent1.getX() > 50) {
            return true;
        }
        else {
            return true ;
        }
    }
}
