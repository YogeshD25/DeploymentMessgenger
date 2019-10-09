package com.neml.deploymentaapproval.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.neml.deploymentaapproval.Database.AppPreference;
import com.neml.deploymentaapproval.Logger.Logg;
import com.neml.deploymentaapproval.NetworkUtils.HttpsTrustManager;
import com.neml.deploymentaapproval.NetworkUtils.SingleRequestQueue;
import com.neml.deploymentaapproval.R;
import com.neml.deploymentaapproval.Utils.Constants;
import com.neml.deploymentaapproval.Utils.utils;

public class MainActivity extends AppCompatActivity {
    EditText email, passWord;
    Button login;
    TextView register;
    boolean isEmailValid, isPasswordValid;
    AppPreference appPreference;
    RequestQueue mRequestQueue;
    TextInputLayout emailError, passError;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpsTrustManager.allowAllSSL();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        appPreference =  new AppPreference(MainActivity.this);
        initUI();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
                if(utils.isNetworkAvailable(MainActivity.this)){
                    postLogin(MainActivity.this, Constants.UrlLinks.login, email.getText().toString(), passWord.getText().toString());
                }else{
                    utils.getSimpleDialog(MainActivity.this,getResources().getString(R.string.app_name),"Internet not Available").show();
                }

//                appPreference.setloginDone(true);
//                appPreference.setUserID(email.getText().toString());
//                appPreference.setPassword(passWord.getText().toString());
//                Intent intent =  new Intent(MainActivity.this,MenuActivity.class);
//                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI() {
        email = (EditText) findViewById(R.id.email);
        passWord = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);
    }

    public void SetValidation() {
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        }  else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        if (passWord.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }
    }

    public void parseData(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                //    firstName = dataobj.getString("name");
                  //  hobby = dataobj.getString("hobby");
                }

                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void checkGoogleApiAvailability() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (resultCode == ConnectionResult.SUCCESS) {
                Logg.d("GoogleApi is available");
            } else {
                apiAvailability.getErrorDialog(this, resultCode, 1).show();
            }
        }
    }

    public void postLogin(final Context mContext, String url, final String userId, final String password) {
        showpDialog();
        Cache cache = new DiskBasedCache(this.getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        Map<String, String> params = new HashMap();
        params.put(Constants.postAttributeName.userId, userId);
        params.put(Constants.postAttributeName.password, password);
        JSONObject parameters = new JSONObject(params);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //TODO: handle success
                try {
                    hidepDialog();
                    if(response != null){
                        String status = response.getString("userId");
                        if(status.equalsIgnoreCase("DINESHP")){
                            appPreference.setUserID(userId);
                            appPreference.setPassword(password);
                            appPreference.setloginDone(true);
                            //Make Intent to pass on next activity
                            Intent intent =  new Intent(mContext,MenuActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            utils.getSimpleDialog(mContext,status).show();
                        }
                    }else{
                        utils.getSimpleDialog(mContext,"Connection Error").show();
                    }


                } catch (JSONException e) {
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